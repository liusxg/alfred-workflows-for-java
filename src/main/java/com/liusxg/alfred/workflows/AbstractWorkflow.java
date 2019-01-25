package com.liusxg.alfred.workflows;

import com.alibaba.fastjson.JSONObject;
import com.liusxg.alfred.workflows.bean.AlfredResponse;
import okhttp3.OkHttpClient;

import java.util.List;

/**
 * Created by liusxg on 2019/1/25.
 */
public abstract class AbstractWorkflow {

    protected static OkHttpClient httpClient = new OkHttpClient();

    public String execute(List<String> params) {
        return JSONObject.toJSONString(concreteExecute(params));
    }

    protected abstract AlfredResponse concreteExecute(List<String> params);
}
