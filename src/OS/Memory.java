//Memory.java

package OS;

public class Memory {
    String name;
    int start;
    int size;
    boolean isAllocated;

    public Memory(String name, int start, int size, boolean isAllocated) {
        this.name = name;
        this.start = start;
        this.size = size;
        this.isAllocated = isAllocated;
    }
}
