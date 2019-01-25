package com.liusxg.alfred.workflows.bean;

/**
 * Created by liuxiaogang on 2019/1/25.
 */
public enum WorkFlows {

    IpLocation("IpLocation", "com.liusxg.alfred.workflows.ip.location.IpLocation");

    public String getName() {
        return name;
    }

    private String name;

    public String getFullClassPath() {
        return fullClassPath;
    }

    private String fullClassPath;

    WorkFlows(String name, String fullClassPath) {
        this.name = name;
        this.fullClassPath = fullClassPath;
    }


}
