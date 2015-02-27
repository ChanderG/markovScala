package markov

class MarkovChain[S](startstate: S){

  var currstate = startstate

  def apply(nstate: S){
    println(currstate + " -> " + nstate)
    currstate = nstate
  }
}
