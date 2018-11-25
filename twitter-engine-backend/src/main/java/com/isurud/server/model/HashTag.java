package com.isurud.server.model;

public class HashTag {

    private String text;
    private long count;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public HashTag(String text, long count) {
        this.text = text;
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
