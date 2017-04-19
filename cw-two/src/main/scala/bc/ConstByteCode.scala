package bc

import vm.VirtualMachine

/**
  * Created by lucieburgess on 17/04/2017.
  * Include the integer in the constructor of the class - this is the only instruction that takes an integer value
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
