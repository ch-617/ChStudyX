package com.xi.gua.chstudyx.grouprecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi.gua.chstudyx.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NORMAL_ITEM = 0;
    private static final int GROUP_ITEM = 1;

    private Context mContext;
    private List<DemoBean> mDataList;
    private LayoutInflater mLayoutInflater;

    private CustomClickListener listener;

    private CustomItemLongClickListener longClickListener;

    private LinearLayout linearLayout;


    public DemoAdapter(Context mContext, List<DemoBean> mDataList, LinearLayout linearLayout) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        this.linearLayout = linearLayout;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == NORMAL_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item_view_item, parent, false);
            NormalItemHolder holder = new NormalItemHolder(mContext, itemView, listener, longClickListener);
            return holder;
        } else if (viewType == GROUP_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item_view_list, parent, false);
            GroupItemHolder holder = new GroupItemHolder(mContext, itemView, listener, longClickListener);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        DemoBean entity = mDataList.get(position);
        if (null == entity) return;

        if (viewHolder instanceof GroupItemHolder) {
            bindGroupItem(entity, (GroupItemHolder) viewHolder);
        } else {
            NormalItemHolder holder = (NormalItemHolder) viewHolder;
            bindNormalItem(entity, holder.out_trade_no, holder.amout, holder.payType, holder.time, holder.today, holder.imageType);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        //第一个要显示时间
        if (position == 0) return GROUP_ITEM;

        String currentDate = mDataList.get(position).getTime();
        int prevIndex = position - 1;
        boolean isDifferent = !mDataList.get(prevIndex).getTime().equals(currentDate);
        return isDifferent ? GROUP_ITEM : NORMAL_ITEM;
    }

    void bindNormalItem(DemoBean entity, TextView out_trade_no, TextView amout, TextView payType,
                        TextView time, TextView today, ImageView imageType) {

        out_trade_no.setText(entity.getOuttradeno());
        amout.setText("￥" + entity.getMoney());
        payType.setText("(" + entity.getPaytype() + ")");
        time.setText(entity.getTimeend());
//        today.setText(entity.getTime());
    }

    void bindGroupItem(DemoBean entity, GroupItemHolder holder) {
//        bindNormalItem(entity, holder.out_trade_no, holder.amout, holder.payType, holder.time, holder.today, holder.imageType);
        holder.group_item_time.setText(entity.getTime());
    }

    public void setClickListener(CustomClickListener clickListener) {
        this.listener = clickListener;
    }

    public void setLongClickListener(CustomItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
}
