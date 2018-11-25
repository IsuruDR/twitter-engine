package com.isurud.server.model;

import java.util.Date;

public class StatusEntry {

    private User user;
    private String text;
    private Date createdAt;

    public StatusEntry(User user, String text, Date createdAt) {
        this.user = user;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
