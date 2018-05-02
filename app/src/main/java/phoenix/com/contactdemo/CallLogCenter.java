package phoenix.com.contactdemo;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guoyali
 * <p>
 * on 2018/5/2.
 * <p>
 * use
 */
public class CallLogCenter implements Center<Context,Paper<List<CallLogInfo>>> {
    private List<CallLogInfo> lists = new ArrayList<>();

    @Override
    public void init(Context context, Paper<List<CallLogInfo>> p) {
        String[] projection = {CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE,CallLog.Calls.DURATION,CallLog.Calls.DATE};
        try {
            Uri callLogUri = CallLog.Calls.CONTENT_URI;

            Cursor cursor = context.getContentResolver().query(callLogUri,projection,null,null,CallLog.Calls.DEFAULT_SORT_ORDER);
            String callLogName;
            String callLogNumber;
            String callLogDate;
            int callLogType;
            int callLogTime;
            String type;
            String time;


            while (cursor.moveToNext()){
                callLogName = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                if (callLogName==null){
                    callLogName = "未知号码";
                }
                callLogNumber = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));

                callLogDate = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
                SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date d = new Date(Long.parseLong(callLogDate));
                callLogDate = DateFormat.format(d);

                callLogType =cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
                if (callLogType == 1){
                    type ="来电";
                }else if (callLogType ==2){
                    type = "拨出";
                }else
                    type = "未接";

                callLogTime = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));
                if (type =="未接"){
                    time ="未接";
                }else {
                    time = timeChange(callLogTime);
                }

                CallLogInfo callLogInfo = new CallLogInfo(callLogName,callLogNumber
                        ,callLogDate,callLogType,time);

                lists.add(callLogInfo);
            }
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        p.sub(lists);
    }

    /**
     * 转化时间
     * @param time
     * @return
     */
    private static String timeChange(int time){
        int h = 0;
        int m = 0;
        int s = 0;
        int temp = time % 3600;
        if (time >3600){
            h = time/3600;
            if(temp!=0){
                if (temp>60){
                    m = temp/60;
                    if (temp%60!= 0 ){
                        s = temp/60;
                    }
                }else {
                    s = temp;
                }
            }
        }else {
            m = time/60;
            if(time%60!=0){
                s = time%60;
            }
        }
        return "通话时长："+ h +"时" + m +"分" + s + "秒";
    }
}
