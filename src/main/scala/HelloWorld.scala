import scala.io.Source

object Main extends App { 

  def Usuage {
    println("Usuage: \nEnter the name of the file to train upon.")
  }
  
  override def main(args: Array[String]){
    //considering only the first parameter of input

    val lines = Source.fromFile(args.head).mkString
    val input = lines.split("\n").mkString
    println(input)
  }
}
