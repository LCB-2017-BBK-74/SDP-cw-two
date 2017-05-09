package bc

import vm.VirtualMachine

/**
  * @author Created by LucieCBurgess on 17/04/2017, reviewed and updated by MattBostock 26/04/2017
  */
class SwapByteCode extends ByteCode with ByteCodeValues {

  /**
    * A unique byte value representing the bytecode. An implementation
    * will set this to the bytecode corresponding to the name of the
    * bytecode in [[ByteCodeValues]]
    * NB. 'bytecode' is a Map of (k,v) pairs where the key is the string name of the bytecode
    * Map.apply(k) returns v, the value associated with the given key
    */
  override val code: Byte = bytecode.apply("iswap")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    * The bytecode operation 'iswap' pops the top values from the virtual machine stack, and pushes them in the
    * opposite order, effectively swapping the top two values of the stack
    * x = VM.pop(); y= VM.pop(); VM.push(x); VM.push(y)
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (res1, vm1) = vm.pop()
    val (res2, vm2) = vm1.pop()
    val vm3 = vm2.push(res1)
    vm3.push(res2)
  }

}
