package vm

import factory.VirtualMachineFactory
import org.scalatest.FunSuite

class PublicVirtualMachineSuite extends FunSuite {
  val vmp = VirtualMachineFactory.virtualMachineParser
  val bcp = VirtualMachineFactory.byteCodeParser
  val vm  = VirtualMachineFactory.virtualMachine

  test("[1] a virtual machine should execute a program") {
    val bc  = vmp.parse("programs/p05.vm")
    val vm2 = vm.execute(bc)
  }

  test("[2] an executed program should give the expected result") {
    val bc  = vmp.parse("programs/p06.vm")
    val vm2 = vm.execute(bc)
    assert(vm2.state.head == 7)
  }

  test("[3] iconst should work correctly") {
    val bc  = vmp.parseString("iconst 1")
    val (bc2, vm2) = vm.executeOne(bc)
    assert(vm2.state.head == 1)
  }

  test("[4] iadd should work correctly") {
    val bc  = vmp.parseString("iconst 1\niconst 2\niadd")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 3)
  }

  test("[5] isub should work correctly") {
    val bc  = vmp.parseString("iconst 1\niconst 2\nisub")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 1)
  }

  test("[6] iswap should work correctly") {
    val bc  = vmp.parseString("iconst 1\niconst 2\niswap")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state(0) == 1)
    assert(next._2.state(1) == 2)
  }

  test("[7] imul should work correctly") {
    val bc  = vmp.parseString("iconst 2\niconst 3\nimul")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 3)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 6)
  }

  test("[8] idiv should work correctly") {
    val bc  = vmp.parseString("iconst 2\niconst 6\nidiv")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 6)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 3)
  }

  test("[9] idec should work correctly") {
      val bc  = vmp.parseString("iconst 2 \nidec")
      var next = vm.executeOne(bc)
      assert(next._2.state.head == 2)
      next = next._2.executeOne(next._1)
      assert(next._2.state.head == 1)
  }

  test("[10] iinc should work correctly") {
    val bc  = vmp.parseString("iconst 2 \niinc")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 3)
  }

  test("[11] idup should work correctly") {
    val bc  = vmp.parseString("iconst 2 \nidup")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
  }

  test("[12] ineg should work correctly") {
      val bc  = vmp.parseString("iconst 5 \nineg")
      var next = vm.executeOne(bc)
      assert(next._2.state.head == 5)
      next = next._2.executeOne(next._1)
      assert(next._2.state.head == -5)
  }

  test("[13] print should work correctly") {
    val bc  = vmp.parseString("iconst 5 \nprint")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 5)
    next = next._2.executeOne(next._1)
    // should print 5 to the console
  }

  test("[14] irem should work correctly") {
    val bc  = vmp.parseString("iconst 2\niconst 5\nirem")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 5)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 1)
  }

}
