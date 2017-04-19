package vendor

/**
  * @author LucieCBurgess on 17/04/2017.
  * Bytecode instructions can be loaded as files (e.g. p01.vm) in which each instruction is on a new line.
  * Or they can be loaded as strings, with the separators between each instruction as a backslash
  * e.g. String representation: "iconst 4\iconst 5\iadd \nprint"
  *
  */
class ProgramParserConcrete extends ProgramParser {

  override type InstructionList = Vector[Instruction]

  /**
    * From Trait ProgramParser definition, parses a file representation of a bytecode program into an `InstructionList`.
    *
    * @param file the file to parse
    * @return an instruction list
    */
  def parse(file: String): InstructionList = {

    import scala.io.Source
    val lines = Source.fromFile(file).getLines
    var instrlist = Vector[Instruction]()
    for (l <- lines) {
      instrlist = instrlist ++: parseString(l)
    }
    instrlist
  }

    /**
    * Parses a string representation of a bytecode program
    * into an `InstructionList`.
    * From the vendor.Instruction class: "The vendor views an instruction as a (`String`, `Vector[Int]`) pair"
    * Firstly, we deal with the fact that instructions may have trailing or leading zeros
    * We split each string into an array of strings (var instrs)
    * For those strings without a space, the Instruction is just the string and we add to the InstructionList, instrsVector
    *
    * @param string the string to parse
    * @return an instruction list
    */
  def parseString(string: String): InstructionList = {

    var instrs :Array[String] = string.split("\n").map(_.trim)
    var instrlist = Vector[Instruction]()

    for (i <- instrs) {

      var str :String = i.toLowerCase
      var num :Vector[Int] = Vector[Int]()

      if (i.contains(" ")) {
        val sep = i.split(" ")
        str = sep(0).toLowerCase
        num = num :+ sep(1).toInt
      }
      instrlist = instrlist :+ new Instruction(str, num)
    }
    instrlist
  }

}
