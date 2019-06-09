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

    public static final int allType=-1;
    public static final int androidType=0;
    public static final int iosType=1;
    public static final int fuliType=2;
    public static final int webType=3;
    public static final int recommendType=4;
    public static final int videoType=5;
    public static final int elseType=6;
    public static final int appType=7;



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

            switch (itemData.optString(kType)){
                case "Android":
                    itemArticle.type=androidType;
                    break;
                case "iOS":
                    itemArticle.type=iosType;
                    break;
                case "福利":
                        itemArticle.type=fuliType;
                    break;
                    default:
                        itemArticle.type=elseType;
                        break;
            }
            itemArticle.url = itemData.optString(kUrl);
            itemArticle.publishTime = itemData.optString(kCreateAt);
            arrayList.add(itemArticle);
        }


    }
}
