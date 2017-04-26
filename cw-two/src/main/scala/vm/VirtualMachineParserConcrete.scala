package vm

import bc.ByteCode
import bc.ByteCodeParserConcrete
import scala.util.control.NonFatal
import vendor.ProgramParserConcrete

/**
  * Created by mattbostock on 22/04/2017.
  * @author Matt Bostock
  */
class VirtualMachineParserConcrete extends VirtualMachineParser {
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
    * @return a vector of bytecodes
    */
   // FIXME document exception bc.InvalidBytecodeException
  def parse(file: String): Vector[ByteCode] = {
    try {
      val bytes = ppc.parse(file).flatMap(i =>
            (Vector(bcp.bytecode.apply(i.name)) ++ i.args.map(_.toByte))
          )
      bcp.parse(bytes)
    } catch {
      case NonFatal(e) => throw new bc.InvalidBytecodeException(e.toString)
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
    * @return a vector of bytecodes
    */
   // FIXME document exception bc.InvalidBytecodeException
   // FIXME need to deduplicate the logic in this function
   // FIXME need to add a test for this function (doesn't seem to have coverage)
  def parseString(str: String): Vector[ByteCode] = {
    try {
      val bytes = ppc.parseString(str).flatMap(i =>
            (Vector(bcp.bytecode.apply(i.name)) ++ i.args.map(_.toByte))
          )
      bcp.parse(bytes)
    } catch {
      case NonFatal(e) => throw new bc.InvalidBytecodeException(e.toString)
    }
  }

}
