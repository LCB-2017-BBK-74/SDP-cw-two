package factory

import bc.{ByteCode, ByteCodeFactory, InvalidBytecodeException}

/**
  * @author Created by lucieburgess on 17/04/2017.
  * Bytecode names: "iconst", "iadd", "isub", "imul", "idiv", "irem","ineg", "iinc", "idec", "idup", "iswap", "print"
  */
class ByteCodeFactoryConcrete extends ByteCodeFactory {

  override def make(byte: Byte, args: Int*): ByteCode = byte match {

    case "iconst" => new ConstByteCode //NB. need to turn string into a Byte

    case _ => throw new InvalidBytecodeException("the Byte provided is not in the list of available ByteCodeValues")
  }

}

//def getFactory(s: String) :AbstractParserFactory = s match {
//case "NYCFactory" => new NYCParserFactory
//case "LondonFactory" => new LondonParserFactory

