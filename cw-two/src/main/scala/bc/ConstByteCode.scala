package bc

import vm.VirtualMachine

/**
  * @author Created by LucieCBurgess on 17/04/2017, reviewed and updated by MattBostock 26/04/2017
  *         Represents a iConst bytecode operation - that is, represents a constant integer value
  * Integer value incorporated in the constructor as this gives a tightly coupled instance variable.
  */
class ConstByteCode(val num: Int) extends ByteCode with ByteCodeValues {

  /**
    * A unique byte value representing the bytecode. An implementation
    * will set this to the bytecode corresponding to the name of the
    * bytecode in [[ByteCodeValues]]
    */
  override val code: Byte = bytecode.apply("iconst")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    * iconst NUM - the iconst instruction pushes the integer value NUM onto the virtual machine stack
    * I have passed in NUM as a constructor argument
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    vm.push(num)
  }

}
