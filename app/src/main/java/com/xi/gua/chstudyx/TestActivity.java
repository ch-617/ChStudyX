package com.xi.gua.chstudyx;

import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.text);
        calculate();
    }

    private void calculate() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd");
        Date date1 = new Date();
        String timeStr = "2020-06-05";
        Date date2 = new Date();
        try {
            date2 = simpleDateFormat1.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        sb.append(date1.toString());
//        for (int i = 0; i < 15; i++) {
//            sb.append("\n").append(simpleDateFormat1.format(date1.getTime() - i * 24*60*60*1000));
//        }
        sb.append(date2.toString());
        for (int i = 0; i < 15; i++) {
            long dateLong = date2.getTime() - i * 24 * 60 * 60 * 1000;
            sb.append("\n").append(simpleDateFormat1.format(dateLong))
                    .append("    ").append(simpleDateFormat2.format(dateLong));
        }
        textView.setText(sb);
    }
}
