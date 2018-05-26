package com.rocflying.gankio.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by liupeng on 2018/5/26.
 */
public class Article extends JsonAble {

    public static final String kResults = "results";
    public static final String kCreateAt = "createdAt";
    public static final String kDesc = "desc";
    public static final String kType = "type";
    public static final String kUrl = "url";


    public ArrayList<ItemArticle> arrayList = new ArrayList<>();

    @Override
    public JSONObject toJson() {
        return null;
    }

    @Override
    public void parseJson(JSONObject jsonObject) {

        if (jsonObject == null) {
            return;
        }

        JSONArray jsonArray = jsonObject.optJSONArray(kResults);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject itemData = jsonArray.optJSONObject(i);
            ItemArticle itemArticle = new ItemArticle();
            itemArticle.desc = itemData.optString(kDesc);
            itemArticle.type = itemData.optString(kType);
            itemArticle.url = itemData.optString(kUrl);
            itemArticle.publishTime = itemData.optString(kCreateAt);
            arrayList.add(itemArticle);
        }


    }
}
