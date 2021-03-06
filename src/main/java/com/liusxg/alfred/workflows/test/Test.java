package com.liusxg.alfred.workflows.test;

import com.liusxg.alfred.workflows.AbstractWorkflow;
import com.liusxg.alfred.workflows.bean.AlfredResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liusxg on 2019/1/25.
 */
public class Test extends AbstractWorkflow {
    @Override
    protected AlfredResponse concreteExecute(List<String> params) {
        return AlfredResponse.builder()
                .items(
                        Arrays.asList(new AlfredResponse.Item("测试", params.toString(), params.toString()))
                )
                .build();
    }
}
