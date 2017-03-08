# Problem 1: 
####Mark and sweep GC
The mark and sweep garbage collector is implemented as a subclass of mandatory_02.garbagecollection.Heap. Appendix B
contains the skeleton code for the class. The mark() and sweep() methods must be
finished.

a) Write the mark()
``` Java
    private void mark(int block) {
        if (block != NULL) {
            this.setFlag(block, REACHABLE);
            mark(this.getPtr1(block));
            mark(this.getPtr2(block));
        }
    }
```

b) Write the sweep()
``` Java
    private void sweep() {
        int addr = 0;
        while (addr < HEAP_SIZE) {

            if (this.getFlag(addr) == GARBAGE) {
                this.addToFreeList(addr, getSize(addr));
            }

            addr = addr + this.getSize(addr);
        }
    }
```

# Problem 2: 
####A compacting garbage collector
a) Write the calculateAdrresses() method!
``` Java
  private int calculateAddresses() {
        int offset = 0;
        int addr = 0;
        while (addr < memory.length)
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
``` 

b) Write the updatePointers() method!
``` Java
    private void updatePointers() {
        int addr = 0;
        while (addr < memory.length) {
            int ptr1 = getPtr1(addr);
            int ptr2 = getPtr2(addr);
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
``` 

c) Write the moveObjects() method!
``` Java
    private void moveObjects() {
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
``` 
