//Request.java

package Disk;

public class Request {
    int id;
    int trackNumber;
    boolean isDispatched;
    public Request(){
        this.id=0;
        this.trackNumber=0;
        this.isDispatched=false;
    }

    public Request(int id, int trackNumber) {
        this.id=id;
        this.trackNumber=trackNumber;
        this.isDispatched=false;
    }
}
