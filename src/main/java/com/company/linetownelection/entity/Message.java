package com.company.linetownelection.entity;

public class Message {
    private String id;
    private Integer votedCount;

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
}
