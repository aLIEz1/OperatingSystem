//Run.java

package OS;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        MemoryAllocationAndRecycling m = new MemoryAllocationAndRecycling();
        int flag0;
        int flag1;
        Scanner in = new Scanner(System.in);
        WorkList works = new WorkList();
        while (true) {
            System.out.print("1:分配内存\n2:回收内存\n3:显示内存分配情况\n4:退出程序\n");
            System.out.print("请选择要执行的操作:");
            flag0 = in.nextInt();

            if (flag0 == 1) {
                System.out.print("1:首次适应算法\n2:最佳适应算法\n3:最坏适应算法\n");
                System.out.print("请选择分配算法:");
                flag1 = in.nextInt();
                String name;
                int size;
                if (flag1 == 1) {
                    System.out.print("请输入作业名:");
                    name = in.next();
                    System.out.print("请输入作业大小:");
                    size = in.nextInt();
                    works.works.add(new Work(name, size));
                    m.firstFit(new Work(name, size));
                } else if (flag1 == 2) {
                    System.out.print("请输入作业名:");
                    name = in.next();
                    System.out.print("请输入作业大小:");
                    size = in.nextInt();
                    works.works.add(new Work(name, size));
                    m.bestFit(new Work(name, size));
                } else if (flag1 == 3) {
                    System.out.print("请输入作业名:");
                    name = in.next();
                    System.out.print("请输入作业大小:");
                    size = in.nextInt();
                    works.works.add(new Work(name, size));
                    m.worstFit(new Work(name, size));
                } else {
                    System.out.println("输入错误");
                }
            } else if (flag0 == 2) {
                System.out.print("请输入要回收的作业名:");
                String name;
                int j = 0;
                name = in.next();
                for (int i = 0; i < works.works.size(); i++) {
                    if (name.equals(works.works.get(i).name)) {
                        m.recycle(works.works.get(i));
                    } else {
                        j++;
                    }
                }
                if (j > works.works.size() - 1) {
                    System.out.println("回收错误");
                }
            } else if (flag0 == 3) {
                m.display();
            } else if (flag0 == 4) {
                break;
            } else {
                System.out.println("输入错误");
            }
        }
    }
}
