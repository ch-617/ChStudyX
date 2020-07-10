package com.xi.gua.chstudyx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.xi.gua.chstudyx.myview.RockerVerticalButton;

public class CustomControlActivity extends AppCompatActivity {
    private RockerVerticalButton rockerVerticalButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_control);
        rockerVerticalButton = findViewById(R.id.rvb);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Log.i("TAG", "offsetY: " + rockerVerticalButton.getmOffsetY());
                }
            }
        }).start();
    }
}
