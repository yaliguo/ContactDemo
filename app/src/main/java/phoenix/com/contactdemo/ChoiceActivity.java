package phoenix.com.contactdemo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by guoyali
 * <p>
 * on 2018/5/2.
 * <p>
 * use 选择界面，通讯录 和 通讯记录
 */
public class ChoiceActivity extends AppCompatActivity {

    private Button goContact;
    private Button goReport;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        goContact = findViewById(R.id.act_choice_go1);
        goReport = findViewById(R.id.act_choice_go2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {

            initClick();

        } else {
            ActivityCompat.requestPermissions(ChoiceActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG}, 0);
        }


    }

    private void initClick() {
        goContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                intent.putExtra("flag", "contact");
                startActivity(intent);
            }
        });

        goReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                intent.putExtra("flag", "report");
                startActivity(intent);
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 0:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED&&grantResults[1] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //权限已授予

                    initClick();

                } else {
                    //权限未授予
                    Toast.makeText(this, "在未授予权限的情况下，程序无法正常工作,请在设置里打开应用权限",
                            Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }
}
