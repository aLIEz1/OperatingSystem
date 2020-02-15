//Run.java

package Disk;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        int start, end, num;
        Scanner in = new Scanner(System.in);
        System.out.println("设定磁道起止道号：");
        System.out.print("起始道号：");
        start=in.nextInt();
        System.out.print("终止道号：");
        end=in.nextInt();
        System.out.print("输入磁盘访问的请求数：");
        num=in.nextInt();
        Work work =new Work(start,end,num);
        Dispatch dispatch =new Dispatch(work);
        dispatch.show();
        System.out.println("=========================");
        System.out.println("    FCFS    ");
        dispatch.FCFS();
        System.out.println("=========================");
        System.out.println("    SSTF    ");
        dispatch.SSTF();
        System.out.println("=========================");
        System.out.println("    SCAN    ");
        dispatch.SCAN();
        System.out.println("=========================");
        System.out.println("    CSCAN    ");
        dispatch.CSCAN();
    }
}
