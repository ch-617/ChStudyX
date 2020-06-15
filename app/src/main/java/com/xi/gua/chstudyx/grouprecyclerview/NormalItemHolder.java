package com.xi.gua.chstudyx.grouprecyclerview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi.gua.chstudyx.R;

import androidx.recyclerview.widget.RecyclerView;

public class NormalItemHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    public TextView out_trade_no;
    public TextView amout;
    public TextView payType;
    public TextView time;
    public TextView today;
    public ImageView imageType;

    private CustomClickListener listener;
    private CustomItemLongClickListener longClickListener;
    private Context context;


    public NormalItemHolder(Context context, View itemView, CustomClickListener listener,
                            CustomItemLongClickListener longClickListener) {
        super(itemView);
        this.context = context;
        this.listener = listener;
        this.longClickListener = longClickListener;
        time = itemView.findViewById(R.id.time);
        out_trade_no = itemView.findViewById(R.id.out_trade_no);
        amout = itemView.findViewById(R.id.amout);
//        today = itemView.findViewById(R.id.today);
        payType = itemView.findViewById(R.id.payType);
        imageType = itemView.findViewById(R.id.imageType);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
//            itemView.setBackgroundResource(R.drawable.recycler_bg);
            listener.setClickListtener(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (longClickListener != null) {
//            itemView.setBackgroundResource(R.drawable.recycler_bg);
            longClickListener.setItemLongClick(v, getAdapterPosition());
        }
        return true;
    }
}
