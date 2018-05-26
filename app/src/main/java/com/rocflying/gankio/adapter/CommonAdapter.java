package com.rocflying.gankio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rocflying.gankio.R;
import com.rocflying.gankio.activity.ActivityWebDetail;
import com.rocflying.gankio.adapter.viewhoder.CommonViewHolder;
import com.rocflying.gankio.entity.ItemArticle;

import java.util.ArrayList;

/**
 * Created by liupeng on 2018/5/26.
 */
public class CommonAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context context;
    private ArrayList<ItemArticle> arrayList;

    public CommonAdapter(Context context, ArrayList<ItemArticle> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.layout_common_viewholder,null);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder,final  int position) {
        holder.setData(arrayList.get(position));
        holder.setItemClickListener(new CommonViewHolder.onItemClickListener() {
            @Override
            public void onItemClick() {
                ActivityWebDetail.openActivity(context,arrayList.get(position).url);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
