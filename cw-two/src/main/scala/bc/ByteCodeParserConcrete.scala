package bc

import factory.ByteCodeFactoryConcrete

/**
  * Created by Lucieburgess on 19/04/2017, reviewed and updated by MattBostock 26/04/2017
  * @author LucieCBurgess, Matt Bostock
  */
class ByteCodeParserConcrete extends ByteCodeParser with ByteCodeValues {

  val bcf: ByteCodeFactory = new ByteCodeFactoryConcrete
  /**
    * Parses a vector of `Byte` into a vector of `ByteCode`.
    *
    * You should use [[ByteCodeValues.bytecode]] to help translate
    * the individual `Bytes' into a correponding [[ByteCode]].
    * NB. bc is a vector of bytes representing bytecodes
    *
    *
    * @param bc a vector of bytes representing bytecodes
    * @return a vector of `ByteCode` objects
    */
  def parse(bc: Vector[Byte]): Vector[ByteCode] = {

    var byteCodeVector: Vector[ByteCode] = Vector[ByteCode]()

    var index = 0

    while (index < bc.length) {

      if (bc(index).equals(bytecode.apply("iconst"))) {
        byteCodeVector = byteCodeVector :+ bcf.make(bc(index), bc(index + 1).toInt)
        index += 2
      } else {
        byteCodeVector = byteCodeVector :+ bcf.make(bc(index))
        index += 1
      }
    }
    byteCodeVector
  }

}
