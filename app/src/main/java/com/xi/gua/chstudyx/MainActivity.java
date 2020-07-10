package com.xi.gua.chstudyx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xi.gua.chstudyx.grouprecyclerview.ClassifyShowActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setTitleName(getString(R.string.app_name));
        startActivity(new Intent(this, CustomControlActivity.class));
    }

    public void image_rendering(View view) {
        startActivity(new Intent(this, ImageRenderingActivity.class));
    }

    public void classify_show(View view) {
        startActivity(new Intent(this, ClassifyShowActivity.class));
    }

    public void test(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }

    public void custom_control(View view) {
        startActivity(new Intent(this, CustomControlActivity.class));
    }
}
