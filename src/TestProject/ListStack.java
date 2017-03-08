/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestProject;

/**
 *
 * @author evenal
 */
public class ListStack<T>  {
    private class StackNode {
        T value;
        StackNode next;

        public StackNode(T value, StackNode next) {
            this.value = value;
            this.next = next;
        }
    }
    
    StackNode stack;
    
    public  void push(T v) {
        stack = new StackNode(v, stack);
    }
    
    public T pop() {
        if (stack == null) throw new StackOverflowError("Underflow");
        
        T v = stack.value;
        stack = stack.next;
        return v;
    }
}
