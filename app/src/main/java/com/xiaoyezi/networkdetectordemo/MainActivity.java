package com.xiaoyezi.networkdetectordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.xiaoyezi.networkdetector.NetStateObserver;
import com.xiaoyezi.networkdetector.NetworkDetector;
import com.xiaoyezi.networkdetector.NetworkType;
import com.xiaoyezi.networkdetector.NetworkUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private NetStateObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.xiaoyezi.networkdetectordemo.R.layout.activity_main);

        final TextView textView = findViewById(com.xiaoyezi.networkdetectordemo.R.id.network);
        textView.setText(NetworkUtils.getNetworkType(this).toString());
        observer = new NetStateObserver() {
            @Override
            public void onDisconnected() {
                Log.d(TAG, "onDisconnected: ");
                textView.setText("onDisconnected");
            }

            @Override
            public void onConnected(NetworkType networkType) {
                Log.d(TAG, "onConnected: " + networkType.toString());
                textView.setText(networkType.toString());
            }
        };
        NetworkDetector.getInstance().addObserver(observer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkDetector.getInstance().removeObserver(observer);
    }
}
