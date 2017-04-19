package vendor

import factory.VirtualMachineFactory
import org.scalatest.FunSuite

class PublicVendorParserSuite extends FunSuite {
  val vp = VirtualMachineFactory.vendorParser

  test("[3] vendor parser should parse a program string correctly") {
    val insts = vp.parseString("iconst 4\niconst 5\niadd\nprint")
    assert(insts.length == 4)
  }

  test("[4] vendor parser should parse a program file correctly") {
    val insts = vp.parse("programs/p03.vm")
    assert(insts.length == 20)

    val all = Vector("iconst", "iconst", "iswap", "iadd", "iconst", "iadd",
      "iconst", "isub", "iconst", "imul", "iconst", "idiv",
      "iconst", "irem", "ineg", "idec", "iinc", "idup", "print", "print")
    for (i <- insts.indices) {
      assert(insts(i).name == all(i))
    }
  }

  test("[5] vendor parser should parse a program string with uppercase letters correctly") {
    val insts = vp.parseString("ICONST 4\nICONST 5\nIADD\nPRINT")
    val all = Vector("iconst","iconst","iadd","print")
    for (i <- insts.indices) {
      assert(insts(i).name == all(i))
    }
  }

  test("[6] vendor parser should parse a program string with trailing or leading spaces correctly") {
    val insts = vp.parseString("ICONST 4 \n ICONST 5\n IADD \n PRINT ")
    val all = Vector("iconst","iconst","iadd","print")
    for (i <- insts.indices) {
      assert(insts(i).name == all(i))
    }
  }


}
