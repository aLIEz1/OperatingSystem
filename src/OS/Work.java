//Work.java

package OS;

import java.util.ArrayList;
import java.util.List;

public class Work {
    String name;
    int size;

    public Work(String name, int size) {
        this.name = name;
        this.size = size;
    }
}

class WorkList {
    List<Work> works = new ArrayList<Work>();
}
