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
public class ArrayStack<T> {
    public static final int MAX = 10;
    T[] stack;
    int top;

    public ArrayStack() {
        stack = (T[])new Object[MAX];
        top = 0;
    }
    
    public void push(T v) {
        if (top >= MAX) throw new StackOverflowError();
        stack[top] = v;
        top++;
    }
    
    public T pop() {
        if (top <= 0) throw new StackOverflowError("Stack underflow");
        top--;
        return stack[top];
    }
    
}
