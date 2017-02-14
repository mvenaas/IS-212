/**
 * This class is the memory model for the exam. Memory is represented
 * by the int array memory. The memory is partitioned into blocks,
 * which can be either objects, or free (unallocated) memory.
 *
 * The memory blocks are adjacent, in other words adding the size of
 * of an object to its address yields the address of the next object.
 *
 * In this simplified context, all objects have two pointers and some
 * data. The pointers are integers, the indices of the objects they
 * point to.
 *
 * The garbage collector adds a header to the front of the object,
 * with the data that is needed by the gc algorithm.
 *
 * The layout is:
 * <table>
 * <tr><th>Field</th><th>Offset</th><th>Description</th></tr>
 * <tr><td>size</td><td>0</td><td>Total size of the object (including
 * the header)</td></tr>
 * <tr><td>Flag(s)</td><td>1</td><td>Used to mark objects</td></tr>
 * <tr><td>Next</td><td>2</td><td>Misnamed. It is a pointer that the
 * garbage collector may use.</td></tr>
 * <tr><td>ptr1</td><td>3</td><td>These two pointers simulate java
 * class type variables</td></tr>
 * <tr><td>ptr2</td><td>4</td></tr>
 * <tr><td>Data</td><td>5</td><td>This is used to vary the size of the
 * objects</td></tr>
 * <tr><td></td><td></td><td></td></tr>
 * <tr><td></td><td></td><td></td></tr>
 * </table>
 *
 * @author evenal
 */
public class Heap
{

    public static final int HEAP_SIZE = 100;
    public static final int NULL = -1; // null pointer

    // flags
    protected static final int FREE = -2; // on  the free list
    protected static final int REACHABLE = -3; // in use
    protected static final int GARBAGE = -4; // potential garbage

    // offsets from start of object
    protected static final int SIZE_OFFSET = 0;
    protected static final int FLAG_OFFSET = 1;
    protected static final int NEXT_OFFSET = 2;
    protected static final int PTR1_OFFSET = 3;
    protected static final int PTR2_OFFSET = 4;
    protected static final int DATA_OFFSET = 5;
    protected static final int HEADER_SIZE = PTR1_OFFSET;

    /**
     * This array represents the memory that is available to the
     * program for object creation.
     *
     * next i+2 points to the next element in linked lists
     * ptr1 i+3 address, i.e. a pointer to an object
     * ptr2 i+4 another pointer these simulate class type variables
     * data i+5 object data (continues to i+size-1)
     *
     */
    int[] memory;
    /**
     * Simulates the root set, i.e. variables within the program
     * that can always be used to reach objects
     */
    int root;

    public Heap() {
        this(HEAP_SIZE);
    }

    public Heap(int size) {
        memory = new int[size];
    }

    // Convenience getters and setters for the objects
    public void setRoot(int addr) {
        root = addr;
    }

    public int getSize(int addr) {
        return memory[addr + SIZE_OFFSET];
    }

    public void setSize(int addr, int size) {
        memory[addr + SIZE_OFFSET] = size;
    }

    public int getFlag(int addr) {
        return memory[addr + FLAG_OFFSET];
    }

    public void setFlag(int addr, int flag) {
        memory[addr + FLAG_OFFSET] = flag;
    }

    public int getNext(int addr) {
        return memory[addr + NEXT_OFFSET];
    }

    public void setNext(int addr, int next) {
        memory[addr + NEXT_OFFSET] = next;
    }

    public int getPtr1(int addr) {
        return memory[addr + PTR1_OFFSET];
    }

    public void setPtr1(int addr, int ptr) {
        memory[addr + PTR1_OFFSET] = ptr;
    }

    public int getPtr2(int addr) {
        return memory[addr + PTR2_OFFSET];
    }

    public void setPtr2(int addr, int ptr) {
        memory[addr + PTR2_OFFSET] = ptr;
    }

    // these are only used to identify objects for testing
    // we don't give them to the student
    public String getData(int addr) {
        char[] buf = new char[getSize(addr) - DATA_OFFSET];
        for (int i = 0; i < buf.length; i++)
            buf[i] = (char) (memory[addr + DATA_OFFSET + i]);
        return new String(buf);
    }

    public void setData(int addr, String data) {
        int max = getSize(addr) - DATA_OFFSET;
        int dl = data.length();
        if (dl > max)
            dl = max;
        for (int i = 0; i < dl; i++) {
            memory[addr + DATA_OFFSET + i] = data.charAt(i);
        }
        for (int i = dl; i < max; i++) {
            memory[addr + DATA_OFFSET + i] = ' ';
        }
    }
}
