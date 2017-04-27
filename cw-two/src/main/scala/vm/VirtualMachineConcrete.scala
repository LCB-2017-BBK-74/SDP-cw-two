package vm

import bc.ByteCode
import scala.collection.immutable.Vector

/**
  * Created by mattbostock on 22/04/2017.
  * @author Matt Bostock
  */
class VirtualMachineConcrete (stack : Vector[Int] = Vector()) extends VirtualMachine {
  /**
    * Executes a vector of bytecodes.
    *
    * Note, that this is an "immutable" object. That is, it
    * will return a new virtual machine after executing each
    * of the bytecode objects in the vector.
    *
    * @param bc a vector of bytecodes
    * @return a new virtual machine
    */
  def execute(bc: Vector[ByteCode]): VirtualMachine = {
    if (bc.length == 0) {
      return new VirtualMachineConcrete(this.state)
    }

    val (next, vm) = this.executeOne(bc)
    return vm.execute(next)
  }

  /**
    * Executes the next bytecode in the vector of bytecodes.
    *
    * This method only executes a single byte code in the vector of bytecodes.
    * It returns a tuple of the new vector of bytecodes (with the executed
    * bytecode removed) and the new virtual machine.
    *
    * You may assume that `bc` contains at least 1 bytecode.
    *
    * @param bc the vector of bytecodes
    * @return a tuple of a new vector of bytecodes and virtual machine
    */
  def executeOne(bc: Vector[ByteCode]): (Vector[ByteCode], VirtualMachine) = {
    (bc.drop(1), bc.head.execute(new VirtualMachineConcrete(this.state)))
  }

  /**
    * Pushes an integer value onto the virtual machine stack.
    *
    * @param value the integer to push
    * @return a new virtual machine with the integer `value` pushed
    */
  def push(value: Int): VirtualMachine = {
    new VirtualMachineConcrete(Vector(value) ++ this.state)
  }

  /**
    * Pops an integer value off of the virtual machine stack.
    *
    * @return (i, vm), where i is the integer popped and vm is the
    *         new virtual machine
    */
  def pop(): (Int, VirtualMachine) = {
    (this.state.head, new VirtualMachineConcrete(this.state.drop(1)))
  }

  /**
    * Returns the state of the virtual machine stack.
    *
    * The value at index 0 is the value on the top of the stack.
    *
    * @return the state of the stack
    */
  def state: Vector[Int] = { this.stack }
}
