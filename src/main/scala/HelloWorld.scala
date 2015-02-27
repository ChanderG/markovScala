import scala.io.Source
import scala.util.parsing.combinator._
import scala.util.matching.Regex

import markov._

object Main extends App { 

  def Usuage {
    println("Usuage: \nEnter the name of the file to train upon.")
  }
  
  override def main(args: Array[String]){
    //considering only the first parameter of input

    //may be split into two to close the file
    val lines = Source.fromFile(args.head).mkString
    val input = lines.split("\n").toList
    //println(input)

    //input is a list of strings
    //MarkovParser.parseTransistions produces a list of states from this string
    //we need one big list of all states
    val states = input.flatMap( i => MarkovParser.parseTransistions(i) )
    //println(states)

    //so given a markov chain and current state and seeing the next state we
    //update the markov chain and the current state
    
    var chain: MarkovChain[String] = new MarkovChain[String]("Start")
    states.map(chain(_))
  }

}

object MarkovParser extends RegexParsers {
  override def skipWhitespace = true  //is this required
  private val word : Parser[String] = regex(new Regex("[a-zA-Z0-9\\ ]*"))
  private val transition : Parser[List[String]] = repsep(word, ",")   //the symbol interleaved with

  def parseTransistions(src: String): List[String] = parseAll(transition, src) match {
      case Success(result, _) => result
      case failure: NoSuccess => List(failure.msg)
  }
}
