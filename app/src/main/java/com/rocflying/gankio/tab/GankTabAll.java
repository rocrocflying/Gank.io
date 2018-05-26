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
public class GankTabAll extends FrameLayout implements OnRefreshListener, OnLoadMoreListener, dataQuery.onDataQueryFinishListenner {
    private Context context;
    private View contentRefreshLayout;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private CommonAdapter adapter;
    private dataQuery query;
    public ArrayList<ItemArticle> articles = new ArrayList<>();

    public GankTabAll(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
        getData();
    }

    private void getData() {
        query.query(false);
        refreshLayout.autoRefresh();
    }

    private void init() {
        query = new dataQuery();
        adapter = new CommonAdapter(context, query.articles);
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

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.setPrimaryColorsId(R.color.colorPrimary,R.color.colorPrimary);
        query.query(true);

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.setPrimaryColorsId(R.color.colorPrimary,R.color.white);

        query.query(false);

    }

    @Override
    public void onDataQueryFinish(int code, ArrayList<ItemArticle> arrayList) {
        articles = arrayList;
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
    }
}
