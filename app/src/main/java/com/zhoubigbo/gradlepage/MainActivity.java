package com.zhoubigbo.gradlepage;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String Channel;
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(),
                    PackageManager.GET_META_DATA);
            Channel = appInfo.metaData.getString("ZHOU_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Channel = "default";
        }
        mTxtChannel = (TextView) findViewById(R.id.txt_main_channel);
        mTxtChannel.setText("渠道：" + Channel);
    }
}
