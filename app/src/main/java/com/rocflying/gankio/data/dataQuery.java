package com.rocflying.gankio.data;

import com.rocflying.gankio.Net.Configs;
import com.rocflying.gankio.Net.NetUtils;
import com.rocflying.gankio.entity.Article;
import com.rocflying.gankio.entity.ItemArticle;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by liupeng on 2018/5/26.
 */
public class dataQuery {

    public ArrayList<ItemArticle> articles = new ArrayList<>();
    private onDataQueryFinishListenner listenner;
    private int page = 1;
    private String url;

    public void query(final boolean loadMore) {
        if (loadMore) {
            page++;
            url = Configs.GET_DATA_ALL_URL.substring(0, Configs.GET_DATA_ALL_URL.length() - 1) + page;
        } else {
            page = 1;
            url = Configs.GET_DATA_ALL_URL;
        }
        NetUtils.getInstance().doGet(url, new NetUtils.NetWorkCallBack() {
            @Override
            public void onResponseFinish(int code, String result) {
                try {
                    if (result == null) {
                        return;
                    }
                    JSONObject jsonObject = new JSONObject(result);
                    Article article = new Article();
                    article.parseJson(jsonObject);
                    if (!loadMore) {
                        if (articles != null && articles.size() > 0) {
                            articles.clear();
                        }
                    }
                    articles.addAll(article.arrayList);
                    listenner.onDataQueryFinish(code, articles);
                } catch (Exception e) {

                }

            }
        });
    }

    public void setDataQueryFinishListenner(onDataQueryFinishListenner listenner) {
        this.listenner = listenner;
    }

    public interface onDataQueryFinishListenner {
        void onDataQueryFinish(int code, ArrayList<ItemArticle> arrayList);
    }
}
