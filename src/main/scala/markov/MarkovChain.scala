package markov

class MarkovChain[S](startstate: S){

  var currstate = startstate
  var transitionMap : Map[S, transitionDetails[S]] = Map[S, transitionDetails[S]]()

  def apply(nstate: S){
    println(currstate + " -> " + nstate)
    transitionMap = addTransition(currstate, nstate)
    currstate = nstate
  }

  def addTransition(pstate: S, nstate: S) = {
   // if the pstate is already has an entry in the transitionMap 
   val transition = 
     if (transitionMap.contains(pstate)) transitionMap(pstate)
     else new transitionDetails[S]()
  
   val newTransition: transitionDetails[S] = transition.addTransition(nstate)  
   transitionMap.updated(pstate, newTransition)  
  }
}

class transitionDetails[S](transitionCounter: Map[S, Int]){
  //if no parameter is used
  def this() = this(Map[S, Int]()) 

  def addTransition(nstate: S) = {
    val count = countFor(nstate);
    new transitionDetails[S](transitionCounter.updated(nstate, count+1));
  }

  def countFor(state: S): Int = {
    if ( transitionCounter.contains(state) ) transitionCounter(state)
    else 0
  }
}
