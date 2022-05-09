package com.company.linetownelection.entity;

public class OutputMessage {
    private String id;
    private Integer votedCount;
    private String time;

    public OutputMessage(String id, Integer votedCount, String time){
        this.id = id;
        this.votedCount = votedCount;
        this.time = time;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVotedCount() {
        return votedCount;
    }

    public void setVotedCount(Integer votedCount) {
        this.votedCount = votedCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
