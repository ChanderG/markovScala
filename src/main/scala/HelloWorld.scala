import scala.io.Source
import scala.util.parsing.combinator._
import scala.util.matching.Regex

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

    input.foreach { i => println( MarkovParser.parseTransistions(i) ) }
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
