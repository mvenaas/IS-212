/**
 * This is an implementation of the LISP2 garbage collector.
 *
 * @author evenal
 */
public class CompactingGC extends Heap
{

    /**
     * pointer to the start of the free area
     */
    int free;


    public CompactingGC() {
        setFree(0);
    }


    public CompactingGC(int size) {
        super(size);
        setFree(0);
    }


    public int alloc(int externalsize, int ptr1, int ptr2, String data) {
        int size = externalsize + HEADER_SIZE;
        if (size > memory.length - free) {
            gc();
            if (size > memory.length - free)
                throw new OutOfMemoryError();
        }
        int addr = free;
        setSize(addr, size);
        setNext(addr, NULL);
        setFlag(addr, GARBAGE);

        setFree(addr + size);
        construct(addr, ptr1, ptr2, data);
        return addr;
    }


    private void setFree(int addr) {
        free = addr;
        setSize(free, memory.length - free);
        setFlag(free, FREE);
    }


    protected void construct(int addr, int ptr1, int ptr2, String data) {
        setPtr1(addr, ptr1);
        setPtr2(addr, ptr2);
        setData(addr, data);
    }


    public void gc() {
        printMemoryMap("Start gc");
        mark(root);
        printMemoryMap("mark complete");
        int newFree = calculateAddresses();
        printMemoryMap("calculated addresses");
        updatePointers();
        printMemoryMap("updated pointers");
        moveObjects();
        setFree(newFree);
        printMemoryMap("gc complete");

    }

    private void mark(int objAddr) {
        if (objAddr != NULL) {
            this.setFlag(objAddr, REACHABLE);
            mark(this.getPtr1(objAddr));
            mark(this.getPtr2(objAddr));
        }
    }

//    Calculate addresses and update pointers
//  When the garbage collector moves an object, it must also set all the variables that point to it to
//  point to its new location. This is done by looping through all blocks of memory. All blocks
//  marked as usable will be moved. We are going to pack the objects densely at the start of the
//  heap, starting at address 0. Each object that is moved will require a block of memory equal to
//  its size, so the new address and object will be the sum of sizes of the objects that were 
//  processed before it. The new address must be stored in the object. It will be needed twice:
//  First to update the variables that point to it, and second when we actually move the object.
//  To update the pointers, we traverse the heap once again. If a usable object contains pointers to
//  other objects, they must be set to the new location of the other object
    /** calculate the addresse each object will be moved to */
    private int calculateAddresses() {
        int offset = 0;
        int addr = 0;
        while (addr < HEAP_SIZE)
        {
            if (getFlag(addr) == FREE || getFlag(addr) == GARBAGE ) {
                offset += getSize(addr);
            }
            else {
                int size = getSize(addr);
               setNext(addr, addr + getSize(addr) - offset);
            }
           addr = addr + getSize(addr);
        }
        return memory.length - offset;
    }
   
    private void updatePointers() {
        //opg 2b//
        int addr = 0;
        while (addr < memory.length) {
            int ptr1 = getPtr1(addr);
            int ptr2 = getPtr2(addr);
            // naive approach that works with this sample data.
            if (ptr1 != NULL && ptr2 != NULL) {
                setPtr1(addr, getNext(addr));
                ptr1 = getPtr1(addr);
                setPtr2(addr, getNext(addr) + getSize(ptr1));
            } else if (ptr1 != NULL) {
                setPtr1(addr, getNext(addr));
            } else if (ptr2 != NULL) {
                setPtr2(addr, getNext(addr));
            }
            addr = addr + getSize(addr);
        }
    }
 
    private void moveObjects() {
        // opg 2c
        int addr = 0;
        int newAddr = 0;
 
        while (addr < memory.length)
        {
            if (getFlag(addr) != FREE && getFlag(addr) != GARBAGE ) {
                if (newAddr !=0 && newAddr != NULL)
                {
                    memCopy(addr, getSize(addr), newAddr);
                }
                newAddr = getNext(addr);
            }
            addr += getSize(addr);
        }
    }


    private void memCopy(int from, int size, int to) {
        for (int i = 0; i < size; i++)
            memory[to + i] = memory[from + i];
    }


    public void printMemoryMap(String header) {
        System.out.println(header);
        System.out.println("\n Addr   Size   FLAG   Next   Ptr1   Ptr2  Data");

        int addr = 0;
        while (addr < free) {
            System.out.format("%6d %6d %6d %6d %6d %6d %s\n",
                    addr, getSize(addr), getFlag(addr), getNext(addr),
                    getPtr1(addr), getPtr2(addr), getData(addr));
            addr += getSize(addr);
        }
        System.out.format("%6d %6d %6d\n",
                free, getSize(free), getFlag(free));
    }


    public static void main(String[] args) {
        CompactingGC heap = new CompactingGC();
        heap.printMemoryMap("Start protram");
        System.out.println("Create ROOT");
        int root = heap.alloc(10, NULL, NULL, "ROOT");
        heap.setRoot(root);
        heap.printMemoryMap("Created root:");
        System.out.println("Create tmp1");
        int tmp = heap.alloc(17, NULL, NULL, "tmp ojb 1");
        System.out.println("Create tmp2");
        tmp = heap.alloc(17, NULL, NULL, "tmp ojb 2");
        System.out.println("Create k1");
        int branch1 = heap.alloc(17, NULL, NULL, "keep1");
        heap.setPtr1(root, branch1);
        heap.printMemoryMap("Created some objects:");
        System.out.println("Create k2");
        int branch2 = heap.alloc(7, NULL, NULL, "keep2");
        heap.setPtr2(root, branch2);
        heap.printMemoryMap("Created keep2");
        System.out.println("Create k22");
        int branch22 = heap.alloc(8, NULL, NULL, "keep22");
        heap.setPtr2(branch2, branch22);
        heap.printMemoryMap("About to trigger gc");
        System.out.println("Create tmp3");
        tmp = heap.alloc(17, NULL, NULL, "tmp ojb 3");
        heap.printMemoryMap("end of program");
    }
}
