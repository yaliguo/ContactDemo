package phoenix.com.contactdemo;

/**
 * Created by guoyali
 * <p>
 * on 2018/5/2.
 * <p>
 * use
 */
public class CallLogInfo {
    public String number ="";
    public String name ="";
    public String date ="";
    public int type ;   // 来电:1，拨出:2,未接:3
    public String time ="";   //通话时长


    public CallLogInfo(String name, String number, String date, int type, String time) {
        this.name = name;
        this.number = number;
        this.date = date;
        this.type = type;
        this.time = time;
    }
}