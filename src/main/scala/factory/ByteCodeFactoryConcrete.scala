package factory

import bc._

/**
  * @author Created by lucieburgess on 17/04/2017.
  * Bytecode names: "iconst", "iadd", "isub", "imul", "idiv", "irem","ineg", "iinc", "idec", "idup", "iswap", "print"
  */
class ByteCodeFactoryConcrete extends ByteCodeFactory with ByteCodeValues {

  override def make(byte: Byte, args: Int*): ByteCode = byte match {

    case byte if bytecode.apply("iadd").equals(byte) => new AddByteCode
    case byte if bytecode.apply("iconst").equals(byte) => new ConstByteCode(args(0))
    case byte if bytecode.apply("idec").equals(byte) => new DecByteCode
    case byte if bytecode.apply("idiv").equals(byte) => new DivByteCode
    case byte if bytecode.apply("idup").equals(byte) => new DupByteCode
    case byte if bytecode.apply("iinc").equals(byte) => new IncByteCode
    case byte if bytecode.apply("imul").equals(byte) => new MulByteCode
    case byte if bytecode.apply("ineg").equals(byte) => new NegByteCode
    case byte if bytecode.apply("print").equals(byte) => new PrintByteCode
    case byte if bytecode.apply("irem").equals(byte) => new RemByteCode
    case byte if bytecode.apply("isub").equals(byte) => new SubByteCode
    case byte if bytecode.apply("iswap").equals(byte) => new SwapByteCode
    case _ => throw new InvalidBytecodeException("invalid bytecode value")
  }

}

