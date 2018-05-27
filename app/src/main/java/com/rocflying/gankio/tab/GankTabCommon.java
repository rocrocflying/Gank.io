package com.rocflying.gankio.tab;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.rocflying.gankio.R;
import com.rocflying.gankio.adapter.CommonAdapter;
import com.rocflying.gankio.data.dataQuery;
import com.rocflying.gankio.entity.ItemArticle;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by liupeng on 2018/5/26.
 */
public class GankTabCommon extends FrameLayout implements OnRefreshListener, OnLoadMoreListener, dataQuery.onDataQueryFinishListenner {
    public Context context;
    public View contentRefreshLayout;
    public SmartRefreshLayout refreshLayout;
    public RecyclerView recyclerView;
    public CommonAdapter adapter;
    public dataQuery query;
    public int type;
    public ArrayList<ItemArticle> articles = new ArrayList<>();

    public GankTabCommon(@NonNull Context context, int type) {
        super(context);
        this.context = context;
        this.type = type;
        init();
        getData();
    }

    public void getData() {
        query.query(false, type);
        refreshLayout.autoRefresh();
    }

    public void init() {
        query = new dataQuery();
        adapter = new CommonAdapter(context, query.articles, type);
        contentRefreshLayout = LayoutInflater.from(context).inflate(R.layout.content_layout, null);
        refreshLayout = (SmartRefreshLayout) contentRefreshLayout.findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerView) contentRefreshLayout.findViewById(R.id.recylerview_content);
        refreshLayout.setRefreshHeader(new BezierCircleHeader(context));
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        addView(contentRefreshLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        query.setDataQueryFinishListenner(this);

    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, R.color.colorPrimary);
        query.query(true, type);

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, R.color.white);
        query.query(false, type);

    }

    @Override
    public void onDataQueryFinish(int code, ArrayList<ItemArticle> arrayList) {
        articles = arrayList;
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
    }
}
