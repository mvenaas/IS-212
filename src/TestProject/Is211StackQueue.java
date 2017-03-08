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
public class Is211StackQueue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ListStack<String> s = new ListStack<>();
        
        s.push("en");
        s.push("to");
        s.push("tre");
        
        System.err.println(s.pop());
        System.err.println(s.pop());
//        System.err.println(s.pop());

        ListQueue<String> q = new ListQueue<>();
        q.enqueue("en");;
        q.enqueue("to");
        q.enqueue("tre");
        q.enqueue("fire");
        q.enqueue("fem");
        q.enqueue("seks");
        q.enqueue("sju");
        q.printState();
        System.err.println(q.dequeue());
        System.err.println(q.dequeue());
        q.printState();
        q.enqueue("åtte");
        q.enqueue("ni"); 
        q.printState();
        System.err.println(q.dequeue());
        System.err.println(q.dequeue());
        q.printState();

        q.enqueue("ti");
        q.enqueue("elve");
        q.enqueue("tolv");
        q.printState();
        System.err.println(q.dequeue());
        System.err.println(q.dequeue());
        q.printState();
        System.err.println(q.dequeue());
        q.printState();
        
        // TODO code application logic here
    }
    
}
