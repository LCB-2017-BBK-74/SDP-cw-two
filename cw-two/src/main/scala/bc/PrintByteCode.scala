package bc

import vm.VirtualMachine

/**
  * Created by lucieburgess on 19/04/2017.
  */
class PrintByteCode extends ByteCode with ByteCodeValues {

  /**
    * A unique byte value representing the bytecode. An implementation
    * will set this to the bytecode corresponding to the name of the
    * bytecode in [[ByteCodeValues]]
    * NB. 'bytecode' is a Map of (k,v) pairs where the key is the string name of the bytecode
    * Map.apply(k) returns v, the value associated with the given key
    */
  override val code: Byte = bytecode.apply("print")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    * The bytecode operation 'print' pops the top value from the virtual machine stack and prints the value to the console.
    * x = VM.pop(); print(x)
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (res1, vm1) = vm.pop()
    println(res1)
    vm1
  }

}
