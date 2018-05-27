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

    public void query(final boolean loadMore, int type) {
        initUrl(type);
        if (loadMore) {
            page++;
            url = url.substring(0, url.length() - 1) + page;
        } else {
            page = 1;
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

    private void initUrl(int type) {
        url = Configs.GET_DATA_ALL_URL;
        switch (type) {
            case Article.allType:
                url = url.replace("#", "all");
                break;
            case Article.androidType:
                url = url.replace("#", "Android");
                break;
            case Article.iosType:
                url = url.replace("#", "iOS");
                break;
            case Article.fuliType:
                url = url.replace("#", "福利");
                break;
            case Article.webType:
                url = url.replace("#", "前端");
                break;
            case Article.videoType:
                url = url.replace("#", "休息视频");
                break;
            case Article.recommendType:
                url = url.replace("#", "瞎推荐");
                break;
            case Article.elseType:
                url = url.replace("#", "拓展资源");
                break;
            case Article.appType:
                url = url.replace("#", "App");
                break;


        }
    }

    public void setDataQueryFinishListenner(onDataQueryFinishListenner listenner) {
        this.listenner = listenner;
    }

    public interface onDataQueryFinishListenner {
        void onDataQueryFinish(int code, ArrayList<ItemArticle> arrayList);
    }
}
