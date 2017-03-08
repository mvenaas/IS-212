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
public class RingBuffer<T> {

    public static final int MAX = 10;
    T[] buffer;
    int front;
    int back;
    int size;

    public RingBuffer() {
        buffer = (T[]) new Object[10];
        front = back = size = 0;

    }

    public void enqueue(T v) {
        if (size >= MAX) throw new Error();
        size++;
        buffer[back] = v;
        back = (back + 1) % MAX;
    }
    
    public T dequeue() {
        if (size <=0) throw new Error();
        size--;
        T v = buffer[front];
        front = (front +1) % MAX;
        return v;
    }
    
    public void printState() {
        System.out.format("Ringbuffer, size=%d, front=%d back=%d, buffer=\n{", size, front, back);
        for (int i=0; i<buffer.length; i++) System.out.format(" %s", null == buffer[i]? null : buffer[i].toString());
        System.out.println(" }");
    }
}
