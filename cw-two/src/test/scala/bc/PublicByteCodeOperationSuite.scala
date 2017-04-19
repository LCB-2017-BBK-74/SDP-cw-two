package bc

import org.scalatest.FunSuite
import vm.VirtualMachine

/**
  * Created by LucieCBurgess on 17/04/2017.
  * Trying to write some tests that check the ByteCode operations before VirtualMachine is implemented.
  * Doesn't look like this is possible because Vector cannot be cast to VirtualMachine.
  * Need to come back to these tests once VirtualMachine is implemented.
  */
class PublicByteCodeOperationSuite extends FunSuite with ByteCodeValues  {

  test("[10] iadd instruction produces correct result") {
    var vm: VirtualMachine = Vector(1,2,3,4,5).asInstanceOf[VirtualMachine]
    val iadd = new AddByteCode
    iadd.execute(vm)
    assert(vm.state(0).equals(3))
  }

}
