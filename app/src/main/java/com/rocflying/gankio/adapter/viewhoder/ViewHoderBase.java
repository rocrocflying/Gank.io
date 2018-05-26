package com.rocflying.gankio.adapter.viewhoder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rocflying.gankio.entity.ItemArticle;

/**
 * Created by liupeng on 2018/5/26.
 */
public  abstract class ViewHoderBase extends RecyclerView.ViewHolder {
    public ViewHoderBase(View itemView) {
        super(itemView);
    }

    public  abstract void setData(ItemArticle itemArticle);
}
