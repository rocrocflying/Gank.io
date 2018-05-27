package com.rocflying.gankio.tab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.rocflying.gankio.R;
import com.rocflying.gankio.adapter.CommonAdapter;
import com.rocflying.gankio.data.dataQuery;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by liupeng on 2018/5/27.
 */
public class GankTabFuli  extends GankTabCommon {
    public GankTabFuli(@NonNull Context context, int type) {
        super(context, type);
    }

    @Override
    public void init() {
        query = new dataQuery();
        adapter = new CommonAdapter(context, query.articles,type);
        contentRefreshLayout = LayoutInflater.from(context).inflate(R.layout.content_layout, null);
        refreshLayout = (SmartRefreshLayout) contentRefreshLayout.findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerView) contentRefreshLayout.findViewById(R.id.recylerview_content);
        refreshLayout.setRefreshHeader(new BezierCircleHeader(context));
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        addView(contentRefreshLayout);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(adapter);

        query.setDataQueryFinishListenner(this);
    }
}
