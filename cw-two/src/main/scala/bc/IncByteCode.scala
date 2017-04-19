package bc

import vm.VirtualMachine

/**
  * Created by lucieburgess on 17/04/2017.
  */
class IncByteCode extends ByteCode with ByteCodeValues {

  /**
    * A unique byte value representing the bytecode. An implementation
    * will set this to the bytecode corresponding to the name of the
    * bytecode in [[ByteCodeValues]]
    * NB. 'bytecode' is a Map of (k,v) pairs where the key is the string name of the bytecode
    * Map.apply(k) returns v, the value associated with the given key
    */
  override val code: Byte = bytecode.apply("iinc")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    * The bytecode operation 'iinc' pops the top value from the virtual machine stack, increments it and pushes the result
    * VM.push(VM.pop()+1)
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    var res1 = vm.pop()._1
    vm.push(res1+1)
    vm
  }

}
