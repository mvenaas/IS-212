/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandatory_03;

/**
 *
 * @author evenal
 */
public class Hp11C {

    private Hp11cEngine engine;
    private Hp11cGui gui;

    public Hp11C() {
        engine = new Hp11cEngine();
        gui = new Hp11cGui(engine);
        gui.setVisible(true);
    }

    public static void main(String[] args) {
        new Hp11C();
    }
}
