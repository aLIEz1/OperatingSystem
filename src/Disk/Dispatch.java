//Dispatch.java

package Disk;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Dispatch {
    Work work;
    Request[] requests;
    int begin; //磁头初始位置
    DecimalFormat decimalFormat = new DecimalFormat("0.000");

    public Dispatch(Work work) {
        this.work = work;
        requests = work.getRandomRequest();
        //将磁头初始位置设置成允许访问磁道号的中间值
        begin = (work.start + work.end) / 2;
    }
    public void show(){
        System.out.println("原始访问序列:");

        for (Request request:requests){
            System.out.print(request.trackNumber+", ");
        }

        System.out.println();
    }

    //先来先服务算法
    public void FCFS() {
        begin = (work.start + work.end) / 2;
        Request[] req = requests;
        double sum = 0;
        List<Integer> distances = new ArrayList<Integer>();
        System.out.println("磁道号寻道范围:" + work.start + "到" + work.end);
        System.out.println("磁头初始位置:" + begin);
        System.out.print("磁道访问顺序:");

        for (Request request : req) {
            System.out.print(request.trackNumber + ", ");
            distances.add(Math.abs(begin - request.trackNumber));
            sum += Math.abs(begin - request.trackNumber);
            begin = request.trackNumber;
        }

        System.out.println();
        System.out.print("移动距离:   ");

        for (Integer distance : distances) {
            System.out.print(distance + ", ");
        }

        System.out.println();
        System.out.println("移动总数:" + sum);
        System.out.println("平均寻道长度:" + decimalFormat.format(sum / req.length));
    }

    //最短寻道时间优先算法
    public void SSTF() {
        begin = (work.start + work.end) / 2;
        Request[] req = requests;
        double sum = 0;
        int t = 0;
        List<Integer> distances = new ArrayList<Integer>();
        List<Integer> orders = new ArrayList<Integer>();
        System.out.println("磁道号寻道范围:" + work.start + "到" + work.end);
        System.out.println("磁头初始位置:" + begin);

        for (int i = 0; i < req.length; i++) {
            int temp = work.end;

            for (int j = 0; j < req.length; j++) {
                if (Math.abs(req[j].trackNumber - begin) < temp && !req[j].isDispatched) {
                    temp = Math.abs(req[j].trackNumber - begin);
                    t = j;
                }
            }

            orders.add(req[t].trackNumber);
            distances.add(temp);
            sum += temp;
            begin = req[t].trackNumber;
            req[t].isDispatched = true;
        }

        display(req, sum, distances, orders);
    }

    //扫描算法
    public void SCAN() {
        begin = (work.start + work.end) / 2;
        Request[] req = requests;
        double sum = 0;
        int direction; //磁头运动方向
        List<Integer> distances = new ArrayList<Integer>();
        List<Integer> orders = new ArrayList<Integer>();
        System.out.println("设定磁头初始化运动方向（1：磁道号增大方向    -1：磁道号减小方向）：");
        direction = new Scanner(System.in).nextInt();
        System.out.println("磁道号寻道范围:" + work.start + "到" + work.end);
        System.out.println("磁头初始位置:" + begin);
        TreeSet<Integer> treeSet1 = new TreeSet<Integer>();
        TreeSet<Integer> treeSet2 = new TreeSet<Integer>();
        for (Request r : req) {
            if (r.trackNumber >= begin) {
                treeSet1.add(r.trackNumber);
            } else {
                treeSet2.add(r.trackNumber);
            }
        }
        switch (direction) {
            case 1:
                for (int temp : treeSet1) {
                    distances.add(Math.abs(temp - begin));
                    orders.add(temp);
                    sum += Math.abs(temp - begin);
                    begin = temp;

                }
                for (int temp : treeSet2.descendingSet()) {
                    distances.add(Math.abs(temp - begin));
                    orders.add(temp);
                    sum += Math.abs(temp - begin);
                    begin = temp;

                }
                break;
            case -1:
                for (int temp : treeSet2.descendingSet()) {
                    distances.add(Math.abs(temp - begin));
                    orders.add(temp);
                    sum += Math.abs(temp - begin);
                    begin = temp;

                }
                for (int temp : treeSet1) {
                    distances.add(Math.abs(temp - begin));
                    orders.add(temp);
                    sum += Math.abs(temp - begin);
                    begin = temp;

                }
                break;
        }
        display(req, sum, distances, orders);
    }

    //循环扫描算法
    public void CSCAN() {
        begin = (work.start + work.end) / 2;
        Request[] req = requests;
        double sum = 0;
        int direction; //磁头运动方向
        List<Integer> distances = new ArrayList<Integer>();
        List<Integer> orders = new ArrayList<Integer>();
        System.out.println("设定磁头初始化运动方向（1：磁道号增大方向    -1：磁道号减小方向）：");
        direction = new Scanner(System.in).nextInt();
        System.out.println("磁道号寻道范围:" + work.start + "到" + work.end);
        System.out.println("磁头初始位置:" + begin);
        TreeSet<Integer> treeSet1 = new TreeSet<Integer>();
        TreeSet<Integer> treeSet2 = new TreeSet<Integer>();
        for (Request r : req) {
            if (r.trackNumber >= begin) {
                treeSet1.add(r.trackNumber);
            } else {
                treeSet2.add(r.trackNumber);
            }
        }
        switch (direction) {
            case 1:
                for (int temp : treeSet1) {
                    distances.add(Math.abs(temp - begin));
                    orders.add(temp);
                    sum += Math.abs(temp - begin);
                    begin = temp;

                }
                for (int temp : treeSet2) {
                    distances.add(Math.abs(temp - begin));
                    orders.add(temp);
                    sum += Math.abs(temp - begin);
                    begin = temp;

                }
                break;
            case -1:
                for (int temp : treeSet2) {
                    distances.add(Math.abs(temp - begin));
                    orders.add(temp);
                    sum += Math.abs(temp - begin);
                    begin = temp;

                }
                for (int temp : treeSet1) {
                    distances.add(Math.abs(temp - begin));
                    orders.add(temp);
                    sum += Math.abs(temp - begin);
                    begin = temp;

                }
                break;
        }
        display(req, sum, distances, orders);
    }

    private void display(Request[] req, double sum, List<Integer> distances, List<Integer> orders) {
        System.out.print("磁道访问顺序:");

        for (Integer order : orders) {
            System.out.print(order + ", ");
        }

        System.out.println();
        System.out.print("移动距离:   ");

        for (Integer distance : distances) {
            System.out.print(distance + ", ");
        }

        System.out.println();
        System.out.println("平均寻道长度:" + decimalFormat.format(sum / req.length));
    }
}
