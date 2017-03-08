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
public class ListQueue<T> {

    private class ListNode {

        T value;
        ListNode next;

        public ListNode(T value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }
    
    
    ListNode front, back;
    
    
    public ListQueue() {
        front = back = new ListNode((T)"header",null);
    }
    
    public void enqueue(T v) {
        back.next = new ListNode(v, null);
        back = back.next;
    }
    
    public T dequeue() {
        if (front == back) throw new Error("Empty qeueu");
        T v = front.value;
        front = front.next;
        return v;
    }
    
    public void printState() {
        System.err.print("ListQueue:");
        for (ListNode ln = front; ln != null; ln = ln.next)
            System.err.format(" %s", ln.value.toString());
        System.err.println();
    }
    

}
