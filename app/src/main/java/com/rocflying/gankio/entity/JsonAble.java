package com.rocflying.gankio.entity;

import org.json.JSONObject;

/**
 * Created by liupeng on 2018/5/26.
 */
public abstract class JsonAble {

    public abstract JSONObject toJson();

    public abstract void parseJson(JSONObject jsonObject);


}
