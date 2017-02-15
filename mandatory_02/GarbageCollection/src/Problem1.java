import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by Erlend on 15.02.2017.
 */
public class Problem1 extends Heap {

    int freeList;


    public Problem1() {
        freeList = NULL;
        addToFreeList(0, memory.length);
    }


    public Problem1(int size) {
        super(size);
        freeList = NULL;
        addToFreeList(0, memory.length);
    }


    /**
     * Add a recycled object to the free list.
     */
    public void addToFreeList(int addr, int size) {
        setSize(addr, size);
        setNext(addr, freeList);
        setFlag(addr, FREE);
        setData(addr, "FREE");
        freeList = addr;
    }


    private void setup() {
        freeList = 0;
        setSize(freeList, memory.length);
        setFlag(freeList, FREE);
        setNext(freeList, NULL);
    }


    public void setRoot(int root) {
        this.root = root;
    }


    /**
     * This method allocates memory, and calls the "constructor"
     * when the program creates a new object.
     *
     * A block of memory, size+HEADER_SIZE memory cells, is
     * retrieved from the free list. A constructor is called to
     * initialize the object.
     *
     * @return the address to the object.
     *
     * @throws OutOfMemory if unable to find a memory block, even
     *                     after garbage collection
     */
    public int alloc(int size, int ptr1, int ptr2, String data) {
        int addr = getMemory(size + HEADER_SIZE);
        if (NULL == addr) {
            gc();
            addr = getMemory(size + HEADER_SIZE);
            if (NULL == addr) throw new OutOfMemoryError();
        }
        construct(addr, ptr1, ptr2, data);
        return addr;
    }


    private int getMemory(int size) {
        int prev = NULL;
        int block = freeList;
        // find the first block >= size
        while (NULL != block && getSize(block) < size) {
            prev = block;
            block = getNext(block);
        }
        // decide what to do with it
        if (NULL == block) return NULL;
        else if (getSize(block) > size + HEADER_SIZE) {
            //block is large enough to split
            int newobj = block + getSize(block) - size;

            setSize(block, newobj - block);
            setSize(newobj, size);
            setFlag(newobj, GARBAGE);
            setNext(newobj, NULL);
            return newobj;
        }
        else {
            // block exact size or slightly larger - allocate entire block
            setNext(prev, getNext(block));
            setFlag(block, GARBAGE);
            return block;
        }
    }


    protected void construct(int addr, int ptr1, int ptr2, String data) {
        setPtr1(addr, ptr1);
        setPtr2(addr, ptr2);
        setData(addr, data);
    }


    public void gc() {
        System.out.println("Starting gc");
        printMemoryMap();
        mark(root);
        System.out.println("Completed mark, Starting sweep");
        printMemoryMap();
        sweep();
        System.out.println("completed gc");
        printMemoryMap();
    }

    // Mark object
    private void mark(int block) {
        if (block != NULL) {
            this.setFlag(block, REACHABLE);
            mark(this.getPtr1(block));
            mark(this.getPtr2(block));
        }
    }



    private void sweep() {
        int addr = 0;
        while (addr < HEAP_SIZE) {

            if (this.getFlag(addr) == GARBAGE) {
                this.addToFreeList(addr, getSize(addr));
            }

            addr = addr + this.getSize(addr);
        }
    }


    public void printMemoryMap() {
        System.out.println("\n Addr   Size   FLAG   Next   Ptr1   Ptr2  Data");

        int addr = 0;
        while (addr < memory.length) {
            System.out.format("%6d %6d %6d %6d %6d %6d %s\n",
                    addr, getSize(addr), getFlag(addr), getNext(addr),
                    getPtr1(addr), getPtr2(addr), getData(addr));
            addr += getSize(addr);
        }
    }


    public static void main(String[] args) {
        Problem1 heap = new Problem1();
        heap.printMemoryMap();
        System.out.println("Create ROOT");
        int root = heap.alloc(10, NULL, NULL, "ROOT");
        heap.setRoot(root);
        heap.printMemoryMap();
        System.out.println("Create tmp1");
        int tmp = heap.alloc(17, NULL, NULL, "tmp ojb 1");
        System.out.println("Create tmp2");
        tmp = heap.alloc(17, NULL, NULL, "tmp ojb 2");
        System.out.println("Create k1");
        int branch1 = heap.alloc(17, NULL, NULL, "keep1");
        heap.setPtr1(root, branch1);
        heap.printMemoryMap();
        System.out.println("Create k2");
        int branch2 = heap.alloc(7, NULL, NULL, "keep2");
        heap.setPtr2(root, branch2);
        heap.printMemoryMap();
        System.out.println("Create k22");
        int branch22 = heap.alloc(8, NULL, NULL, "keep22");
        heap.setPtr2(branch2, branch22);
        heap.printMemoryMap();
        System.out.println("Create tmp3");
        tmp = heap.alloc(17, NULL, NULL, "tmp ojb 3");
        heap.printMemoryMap();
    }
}