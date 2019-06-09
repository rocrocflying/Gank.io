package com.rocflying.gankio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rocflying.gankio.R;
import com.rocflying.gankio.activity.ActivityPictureView;
import com.rocflying.gankio.activity.ActivityWebDetail;
import com.rocflying.gankio.adapter.viewhoder.CommonViewHolder;
import com.rocflying.gankio.adapter.viewhoder.FuliViewHolder;
import com.rocflying.gankio.adapter.viewhoder.ViewHoderBase;
import com.rocflying.gankio.entity.Article;
import com.rocflying.gankio.entity.ItemArticle;

import java.util.ArrayList;

/**
 * Created by liupeng on 2018/5/26.
 */
public class CommonAdapter extends RecyclerView.Adapter<ViewHoderBase> {

    private Context context;
    private ArrayList<ItemArticle> arrayList;
    private int type;

    public CommonAdapter(Context context, ArrayList<ItemArticle> arrayList, int type) {
        this.context = context;
        this.arrayList = arrayList;
        this.type = type;
    }

    @Override
    public ViewHoderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Article.allType:
                View commonView = LayoutInflater.from(context).inflate(R.layout.layout_common_viewholder, null);
                return new CommonViewHolder(commonView);
            case Article.fuliType:
                View fuliView = LayoutInflater.from(context).inflate(R.layout.layout_fuli_viewholder, null);
                return new FuliViewHolder(fuliView, context);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHoderBase holder, final int position) {
        switch (getItemViewType(position)) {
            case Article.allType:
                setComonData(holder, position);
                break;
            case Article.fuliType:
                setFuliData(holder, position);
                break;
        }

    }

    private void setFuliData(ViewHoderBase holder, int position) {
        final ItemArticle itemArticle = arrayList.get(position);
        ((FuliViewHolder) holder).setData(itemArticle);
        ((FuliViewHolder) holder).setPictureListner(new FuliViewHolder.onPictureListner() {
            @Override
            public void clickPicture() {
                ActivityPictureView.openActivityPictureView(context,itemArticle.url);
            }
        });
    }

    private void setComonData(ViewHoderBase holder, int position) {
        final ItemArticle itemArticle = arrayList.get(position);
        ((CommonViewHolder) holder).setData(itemArticle);
        ((CommonViewHolder) holder).setItemClickListener(new CommonViewHolder.onItemClickListener() {
            @Override
            public void onItemClick() {
                ActivityWebDetail.openActivity(context, itemArticle.url, itemArticle.desc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (type == Article.fuliType) {
            return Article.fuliType;
        } else {
            return Article.allType;
        }
    }
}
