package com.xi.gua.chstudyx.test;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi.gua.chstudyx.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ImageView iv_back;
    private TextView tv_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setTitleName(String titleName) {
        iv_back = findViewById(R.id.back);
        tv_title = findViewById(R.id.titleName);
        if (iv_back != null) {
            iv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            tv_title.setText(titleName);
        }
    }
}
