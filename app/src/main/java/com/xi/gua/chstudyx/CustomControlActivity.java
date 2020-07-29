package com.xi.gua.chstudyx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.xi.gua.chstudyx.myview.RockerVerticalButton;
import com.xi.gua.chstudyx.myview.TrimHorizontalButton;

public class CustomControlActivity extends AppCompatActivity {
    private RockerVerticalButton rockerVerticalButton;
    private TrimHorizontalButton trimHorizontalButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_control);
        rockerVerticalButton = findViewById(R.id.rvb);
        trimHorizontalButton = findViewById(R.id.thv);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Log.i("TAG", "offsetY: " + rockerVerticalButton.getmOffsetY());
                }
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    Log.i("TAG", "offsetX: " + trimHorizontalView.getmOffsetX());
//                }
//            }
//        }).start();
    }
}
