/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hp11c;

/**
 *
 * @author evenal
 */
public class Main {

    private Hp11cEngine engine;
    private Hp11cGui gui;

    public Main() {

//        Stack<Integer> x = new Stack<>(10);
//        x.add(1);
//        x.add(2);
//        x.add(3);
//        System.out.println(x.getSize());

        engine = new Hp11cEngine();
        gui = new Hp11cGui(engine);
        gui.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
