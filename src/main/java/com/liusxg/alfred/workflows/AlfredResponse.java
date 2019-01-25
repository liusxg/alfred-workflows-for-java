package com.liusxg.alfred.workflows;

import lombok.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liusxg on 2019/1/25.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlfredResponse {
    private List<Item> items;

    public static AlfredResponse error(String msg) {
        return AlfredResponse.builder()
                .items(
                        Arrays.asList(new AlfredResponse.Item("出现异常", msg, ""))
                )
                .build();
    }

    @Data
    @AllArgsConstructor
    public static class Item {
        private String title;
        private String subtitle;
        private String arg;
    }
}
