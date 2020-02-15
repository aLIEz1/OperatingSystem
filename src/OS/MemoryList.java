//MemoryList.java

package OS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemoryList {
    List<Memory> memoryList = new ArrayList<Memory>();

    public MemoryList() {
        int start = 1024;
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            assert false;
            int size = rand.nextInt(256) + 256;
            memoryList.add(new Memory(getRandomString(3),
                    start, size, false));
            start += size;
        }
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
