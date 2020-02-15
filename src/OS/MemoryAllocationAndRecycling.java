//MemoryAllocationAndRecycling.java

package OS;

public class MemoryAllocationAndRecycling {
    MemoryList memoryList = new MemoryList();

    public void firstFit(Work work) {
        int index = 0;
        //遍历memoryList找出第一个能满足work大小的区块
        for (int i = 0; i < memoryList.memoryList.size(); i++) {
            if (work.size <= memoryList.memoryList.get(i).size) {
                memoryList.memoryList.get(i).size -= work.size;
                memoryList.memoryList.add(i + 1, new Memory(work.name,
                        memoryList.memoryList.get(i).start + memoryList.memoryList.get(i).size,
                        work.size, true));
                System.out.println("内存分配成功！");
                break;
            }
            index++;
        }
        if (index >= memoryList.memoryList.size()) {
            System.out.println("内存分配失败");
        }
    }

    public void bestFit(Work work) {
        int min = 10000;
        int index = 0;
        int j = 0;
        //遍历memoryList从中找出满足work并且最小的区块
        for (int i = 0; i < memoryList.memoryList.size(); i++) {
            if (memoryList.memoryList.get(i).size - work.size >= 0) {
                if (memoryList.memoryList.get(i).size - work.size < min) {
                    min = memoryList.memoryList.get(i).size - work.size;
                    index = i;
                }

            } else {
                j++;
            }

        }
        Allocation(work, index, j);
    }

    private void Allocation(Work work, int index, int j) {
        if (j >= memoryList.memoryList.size() - 1) {
            System.out.println("内存分配失败");
        } else {
            memoryList.memoryList.get(index).size -= work.size;
            memoryList.memoryList.add(index + 1, new Memory(work.name,
                    memoryList.memoryList.get(index).start + memoryList.memoryList.get(index).size,
                    work.size, true));
            System.out.println("内存分配成功！");
        }
    }

    public void worstFit(Work work) {
        int max = 0;
        int index = 0;
        int j = 0;
        //遍历memoryList从中找出满足work并且最大的区块
        for (int i = 0; i < memoryList.memoryList.size(); i++) {
            if (memoryList.memoryList.get(i).size >= work.size) {
                if (memoryList.memoryList.get(i).size - work.size > max) {
                    max = memoryList.memoryList.get(i).size - work.size;
                    index = i;
                }

            } else {
                j++;
            }
        }
        Allocation(work, index, j);
    }

    public void recycle(Work work) {
        int j = 0;
        //遍历memoryList从中找出和work名字一样的区块并删除
        for (int i = 0; i < memoryList.memoryList.size(); i++) {
            if (memoryList.memoryList.get(i).name.equals(work.name)) {
                memoryList.memoryList.get(i - 1).size += work.size;
                memoryList.memoryList.remove(i);
                System.out.println("内存回收成功！");
            } else {
                j++;
            }
        }
        if (j > memoryList.memoryList.size() - 1) {
            System.out.println("内存回收失败");
        }
    }

    public void display() {
        System.out.println("区号\t\t起始位置\t\t区间长度\t\t是否分配");
        for (int i = 0; i < memoryList.memoryList.size(); i++) {
            System.out.print(memoryList.memoryList.get(i).name);
            System.out.print("\t\t");
            System.out.print(memoryList.memoryList.get(i).start);
            System.out.print("\t\t");
            System.out.print(memoryList.memoryList.get(i).size);
            System.out.print("\t\t\t");
            System.out.println(memoryList.memoryList.get(i).isAllocated);
        }
    }
}
