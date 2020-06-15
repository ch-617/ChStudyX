package com.xi.gua.chstudyx.grouprecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.xi.gua.chstudyx.R;

import java.util.ArrayList;
import java.util.List;

public class ClassifyShowActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private List<DemoBean> mDataList = new ArrayList<>();

    private DemoAdapter mAdapter;
    private List<DemoBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_show);
        initView();
    }

    private void initView() {
        mDataList = getData();
        mAdapter = new DemoAdapter(this, mDataList, null);
        recyclerView = findViewById(R.id.recyclerView);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
    }

    private List<DemoBean> getData() {
        for (int i = 0; i < 3; i++) {
            DemoBean bean = new DemoBean();
            bean.setTimeend("2014-12-12 12:45:60");
            bean.setTime("2016-02-02");
            bean.setPaytype("微信支付");
            bean.setMoney("0.02");
            bean.setOuttradeno("P2013123123156");
            mDataList.add(bean);

        }

        for (int i = 0; i < 3; i++) {
            DemoBean bean = new DemoBean();
            bean.setTimeend("2016-02-06 12:45:60");
            bean.setTime("2016-02-06");
            bean.setPaytype("支付宝支付");
            bean.setMoney("0.04");
            bean.setOuttradeno("P2013123123156");
            mDataList.add(0, bean);
        }
        for (int i = 0; i < 3; i++) {
            DemoBean bean = new DemoBean();
            bean.setTimeend("2016-02-06 12:45:60");
            bean.setTime("2016-02-06");
            bean.setPaytype("唐人支付");
            bean.setMoney("0.04");
            bean.setOuttradeno("P2013123123156");
            mDataList.add(0, bean);
        }
        for (int i = 0; i < 3; i++) {
            DemoBean bean = new DemoBean();
            bean.setTimeend("2016-02-08 12:45:60");
            bean.setTime("2016-02-08");
            bean.setPaytype("QQ支付");
            bean.setMoney("0.04");
            bean.setOuttradeno("P2013123123156");
            mDataList.add(0, bean);
        }

        return mDataList;
    }
}
