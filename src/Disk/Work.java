//Work.java

package Disk;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Work {
    int start;
    int end;
    int requestNumber;

    public Work() {
        this.start = 0;
        this.end = 0;
        this.requestNumber = 0;
    }

    public Work(int start, int end, int num) {
        this.start=start;
        this.end=end;
        this.requestNumber=num;
    }

    public Request[] getRandomRequest() {
        Request[] request = new Request[requestNumber];
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        int i=0;
        do {
            set.add((int) (random.nextInt(end - start) + start));
        } while (set.size() != requestNumber);
        for (int temp:set){
            request[i]=new Request(i+1,temp);
            i++;
        }
        return request;
    }
}
