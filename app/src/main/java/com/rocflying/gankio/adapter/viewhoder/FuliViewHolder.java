package com.rocflying.gankio.adapter.viewhoder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rocflying.gankio.R;
import com.rocflying.gankio.entity.ItemArticle;

/**
 * Created by liupeng on 2018/5/27.
 */

public class FuliViewHolder extends ViewHoderBase implements View.OnClickListener {

    private ImageView pic;
    private Context context;
    private onPictureListner listner;

    public FuliViewHolder(View itemView) {
        super(itemView);

    }

    public FuliViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        pic = (ImageView) itemView.findViewById(R.id.iv_girl);
        pic.setOnClickListener(this);
    }

    @Override
    public void setData(ItemArticle itemArticle) {
        Glide.with(context).load(itemArticle.url).centerCrop().into(pic);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_girl) {
            listner.clickPicture();
        }
    }

    public void setPictureListner(onPictureListner listner) {
        this.listner = listner;
    }

    public interface onPictureListner {
        void clickPicture();
    }
}
