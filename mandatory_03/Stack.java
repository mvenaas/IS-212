package mandatory_03;

/**
 * Created by Christian on 3/7/2017.
 */
public class Stack <E> {

    private StackItem<E> currTop;
    private int size;
    private int max;

    /**
     * Denne klassen her skal representere alle elementene som ligger i stacken.
     * Den peker også på neste i stack.
     * @param <E>
     */
    private static class StackItem<E> {
        private E e;
        private StackItem<E> next;
    }

    /**
     * Opprett en tom stack.
     */
    public Stack(int max) {
        currTop = null;
        size = 0;
        this.max = max;

    }

    /**
     * Legger til et item i stack'en..
     * @param e     elementet som skal legges til..
     */
    public void add(E e){
        if ((size+1) == max) throw new StackOverflowError("Max items in stack is reached");
        StackItem<E> old = currTop;             // lagre den gammle toppen
        currTop = new StackItem<>();           // opprette en ny en
        currTop.e = e;                          // legge til objektet til den nye toppen
        currTop.next = old;                     // sette gammel top til neste verdi
        size++;                                 // øke size

    }

    /**
     * henter første element i stacken og fjerner den.
     * @return
     */
    public E getFirst() {
        if (currTop == null) return null;   // sjekker om det er noe å hente i listen.. om ikke returner null
        E output = currTop.e;               // lagrer verdien..
        currTop = currTop.next;             // setter til neste verdi// sletter toppen av stacken
        size --;
        return output;

    }

    public E showFirst() {
        return currTop.e;
    }

    public int getSize() {
        return size;
    }




}
