package bc

import org.scalatest.FunSuite
import vm.VirtualMachine

/**
  * Created by lucieburgess on 17/04/2017.
  */
class PublicByteCodeOperationSuite extends FunSuite with ByteCodeValues  {

  test("[10] byte code field should return the correct bytecode") {
    val code = Vector(bytecode("iconst"), 4.toByte, bytecode("iconst"), 5.toByte, bytecode("iadd"))
    assert(code(0).equals(bytecode("iconst")
      assert(code(1).equals(bytecode("iconst")

      var vm = new VirtualMachine()


  }

}
