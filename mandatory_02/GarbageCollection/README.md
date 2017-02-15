# Problem 1: Mark and sweep GC
The mark and sweep garbage collector is implemented as a subclass of Heap. Appendix B
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