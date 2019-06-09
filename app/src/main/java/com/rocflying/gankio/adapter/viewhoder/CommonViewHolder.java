package com.rocflying.gankio.adapter.viewhoder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rocflying.gankio.R;
import com.rocflying.gankio.entity.Article;
import com.rocflying.gankio.entity.ItemArticle;

/**
 * Created by liupeng on 2018/5/26.
 */
public class CommonViewHolder extends ViewHoderBase implements View.OnClickListener {

    private TextView title;
    private RelativeLayout itemLayout;
    private TextView dateTextView;
    private View tagView;
    private onItemClickListener listener;

    public CommonViewHolder(View itemView) {
        super(itemView);
        itemLayout = (RelativeLayout) itemView.findViewById(R.id.layout_item);
        title = (TextView) itemView.findViewById(R.id.tv_title);
        tagView = (View) itemView.findViewById(R.id.view_tag);
        dateTextView=(TextView)itemView.findViewById(R.id.tv_date);
        itemLayout.setOnClickListener(this);
    }

    @Override
    public void setData(ItemArticle itemArticle) {
        title.setText(itemArticle.desc);
        dateTextView.setText(itemArticle.publishTime);
        switch (itemArticle.type) {
            case Article.androidType:
                tagView.setBackgroundColor(Color.parseColor("#D9AB7F"));
                break;
            case Article.iosType:
                tagView.setBackgroundColor(Color.parseColor("#55A340"));
                break;
            case Article.fuliType:
                tagView.setBackgroundColor(Color.parseColor("#E8DDD7"));
                break;
            case Article.elseType:
                tagView.setBackgroundColor(Color.parseColor("#169588"));
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_item:
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
