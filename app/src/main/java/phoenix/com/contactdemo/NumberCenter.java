package phoenix.com.contactdemo;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import android.provider.ContactsContract.CommonDataKinds.Phone;

/**
 * Created by guoyali
 * <p>
 * on 2018/5/2.
 * <p>
 * use 通讯录
 */
public class NumberCenter implements Center<Context,Paper<List<PhoneInfo>>>{


    private List<PhoneInfo> lists = new ArrayList<>();

    @Override
    public void init(Context context,Paper<List<PhoneInfo>> p) {
        Cursor cursor=context.getContentResolver().query(Phone.CONTENT_URI,null,null,null,null);
        String phoneNumber;
        String phoneName;
        while (cursor.moveToNext()){
            phoneNumber=cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
            phoneName=cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
            PhoneInfo phoneInfo = new PhoneInfo(phoneName,phoneNumber);
            lists.add(phoneInfo);
            System.out.println(phoneName+phoneNumber);
        }
        p.sub(lists);
    }


}
