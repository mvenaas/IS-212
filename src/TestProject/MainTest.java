package TestProject;

import javax.sound.midi.Soundbank;


public class MainTest {

    public static void main(String[]args){
//        ArrayStack<String> s = new ArrayStack<>();
//        s.push("en");
//        s.push("toi");
//        System.out.println(s.pop());
//        System.out.println(s.pop());
//        System.out.println(s.pop());

//        ListStack<String> s = new ListStack<>();
//        s.push("en");
//        s.push("toi");
//        System.out.println(s.pop());
//        System.out.println(s.pop());

        ListQueue q = new ListQueue();
        q.enqueue("en");
        q.enqueue("to");
        System.out.println(q.dequeue());



    }
}
