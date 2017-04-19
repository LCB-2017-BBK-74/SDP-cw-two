package bc

import factory.VirtualMachineFactory
import org.scalatest.FunSuite

class PublicByteCodeFactorySuite extends FunSuite with ByteCodeValues {
  val bcf = VirtualMachineFactory.byteCodeFactory

  test("[5] all bytecodes should be made by factory") {
    // Tests that each bytecode (modulo "iconst") can be made.
    for ((name, code) <- (bytecode - "iconst")) {
      val bc = bcf.make(code)
      assert(bc.code.equals(code), "invalid bytecode value")
    }
  }

  test("[6] the iconst bytecode behaves as it should") {
    // Test the iconst bytecode
    val code = bytecode("iconst")
    val bc = bcf.make(code, 4)
    assert(bc.code == bytecode("iconst"))
  }

  test("[3] an invalid bytecode should throw an exception") {
    intercept[InvalidBytecodeException] {
      bcf.make(99)
    }
  }

}
