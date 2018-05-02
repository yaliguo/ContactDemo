package phoenix.com.contactdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String flag = getIntent().getStringExtra("flag");
        if(TextUtils.isEmpty(flag)){
            Log.e(getLocalClassName(),"flag is null");
            return;
        }

        if(flag.equals("contact")){
            new NumberCenter().init(this, new Paper<List<PhoneInfo>>() {
                @Override
                public void sub(List<PhoneInfo> phoneInfos) {
                    RecyclerView listV =  findViewById(R.id.act_main_list);
                    ContactAdapter contactAdapter = new ContactAdapter(new ContactAdapter.SimpleItemCallBack());
                    listV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    contactAdapter.submitList(phoneInfos);
                    listV.setAdapter(contactAdapter);
                }
            });

        }else {
            new CallLogCenter().init(this, new Paper<List<CallLogInfo>>() {
                @Override
                public void sub(List<CallLogInfo> phoneInfos) {
                    RecyclerView listV =  findViewById(R.id.act_main_list);
                    ReportAdapter demoAdapter = new ReportAdapter(new ReportAdapter.SimpleItemCallBack2());
                    listV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    demoAdapter.submitList(phoneInfos);
                    listV.setAdapter(demoAdapter);
                }
            });
        }

    }
}
