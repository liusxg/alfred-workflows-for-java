package com.liusxg.alfred.workflows.ip.location;

import com.alibaba.fastjson.JSONObject;
import com.liusxg.alfred.workflows.AbstractWorkflow;
import com.liusxg.alfred.workflows.bean.AlfredResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.liusxg.alfred.workflows.bean.NetworkConstants.USER_AGENT;
import static com.liusxg.alfred.workflows.bean.NetworkConstants.USER_AGENT_VALUE;

/**
 * Created by liusxg on 2019/1/25.
 */
@Slf4j
public class IpLocation extends AbstractWorkflow {

    private static final String taobaoIp = "http://ip.taobao.com/service/getIpInfo.php?ip=%s";
    @Override
    protected AlfredResponse concreteExecute(List<String> params) {
        Request request = new Request.Builder()
                .header(USER_AGENT, USER_AGENT_VALUE)
                .url(String.format(taobaoIp, params.get(0)))
                .build();
        AlfredResponse alfredResponse = AlfredResponse.builder().build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject json = JSONObject.parseObject(response.body().string());
                if (json.getInteger("code").equals(0)) {
                    StringBuilder subTitle = new StringBuilder();
                    JSONObject dataJson = json.getJSONObject("data");

                    subTitle.append("[").append(dataJson.getString("isp")).append("] ")
                            .append(dataJson.getString("country")).append(" ")
                            .append(dataJson.getString("area")).append(" ")
                            .append(dataJson.getString("region")).append(" ")
                            .append(dataJson.getString("city"));
                    alfredResponse.setItems(
                            Arrays.asList(
                                    new AlfredResponse.Item(params.get(0), subTitle.toString(), "")
                            )
                    );
                } else {
                    alfredResponse = AlfredResponse.error("服务提供商出现异常：" + json.toJSONString());
                }
            } else {
                alfredResponse = AlfredResponse.error(response.body().string());
            }
        } catch (IOException e) {
            alfredResponse = AlfredResponse.error(e.getMessage());
        }
        return alfredResponse;
    }
}
