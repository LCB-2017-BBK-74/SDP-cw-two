package vm

import bc.{ByteCode, ByteCodeParserConcrete, ByteCodeValues, InvalidBytecodeException}
import vendor.{Instruction, ProgramParserConcrete}
import scala.util.control.NonFatal

/**
  * Created by mattbostock on 22/04/2017, reviewed and updated by LucieCBurgess 29/04/2017
  * @author Matt Bostock, LucieCBurgess
  * Note, this class uses the vendor's parser to parse a file of bytecode into a vector[Instruction]
  * It then uses the adapter design pattern to write an adapter that translates a vector of
  * [venddor.Instruction] to a vector of [Byte] and then [bc.ByteCode] using the ByteCodeParser.
  * NB. This class extends ByteCodeValues for access to the bytecode field.
  */
class VirtualMachineParserConcrete extends VirtualMachineParser with ByteCodeValues {

  val ppc = new ProgramParserConcrete
  val bcp = new ByteCodeParserConcrete

  /**
    * Returns a vector of [[bc.ByteCode]].
    *
    * This method parses a file into a vector of bytecode objects.
    * Note, this method should throw a [[bc.InvalidBytecodeException]]
    * if it fails to parse a program file correctly.
    *
    * @param file the file containing a program
    * @throws [bc.InvalidBytecodeException] if file fails to parse correctly
    * @return a vector of bytecodes
    */
  def parse(file: String): Vector[ByteCode] = {
    try {
      val instrs = ppc.parse(file)
      val bytes = instructionsToBytes(instrs)
      bcp.parse(bytes)
    } catch {
      case NonFatal(ex) => throw new InvalidBytecodeException(ex.toString)
    }
  }

  /**
    * Returns a vector of [[bc.ByteCode]].
    *
    * This method parses a string into a vector of bytecode objects.
    * Note, this method should throw a [[bc.InvalidBytecodeException]]
    * if it fails to parse a program string correctly.
    *
    * @param str a string containing a program
    * @throws (classOf[bc.InvalidBytecodeException) if string fails to parse correctly
    * @return a vector of bytecodes
    */

  def parseString(str: String): Vector[ByteCode] = {
    try {
      val instrs = ppc.parseString(str)
      val bytes = instructionsToBytes(instrs)
      bcp.parse(bytes)
    } catch {
      case NonFatal(ex) => throw new InvalidBytecodeException(ex.toString)
    }
  }

  /**
    *
    * @param instrs, a vector[Instruction] to translate to a vector[Byte] using the adapter pattern
    * @return bytes, the vector of Bytes.
    */
  private def instructionsToBytes(instrs: Vector[Instruction]): Vector[Byte] = {
    instrs.flatMap(i => (Vector(bcp.bytecode.apply(i.name)) ++ i.args.map(_.toByte)))
  }

}
