package com.rocflying.gankio.adapter.viewhoder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rocflying.gankio.R;
import com.rocflying.gankio.entity.ItemArticle;

/**
 * Created by liupeng on 2018/5/26.
 */
public class CommonViewHolder extends ViewHoderBase implements View.OnClickListener {

    private TextView title;
    private onItemClickListener listener;

    public CommonViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.tv_title);
        title.setOnClickListener(this);
    }

    @Override
    public void setData(ItemArticle itemArticle) {
        title.setText(itemArticle.desc);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                listener.onItemClick();
                break;

        }

    }

    public void setItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onItemClick();
    }
}
