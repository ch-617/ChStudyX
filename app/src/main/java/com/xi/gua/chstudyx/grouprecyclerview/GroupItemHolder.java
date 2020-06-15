package com.xi.gua.chstudyx.grouprecyclerview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xi.gua.chstudyx.R;

public class GroupItemHolder extends NormalItemHolder {
    public TextView group_item_time;

    public GroupItemHolder(Context context, View itemView, CustomClickListener listener, CustomItemLongClickListener longClickListener) {
        super(context, itemView, listener, longClickListener);
        group_item_time = itemView.findViewById(R.id.group_item_time);
    }
}
