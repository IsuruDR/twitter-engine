package com.isurud.server.model;

import java.util.List;

public class SearchResponse {

    private List<StatusEntry> statusEntries;
    private List<HashTag> hashTags;

    public List<StatusEntry> getStatusEntries() {
        return statusEntries;
    }

    public void setStatusEntries(List<StatusEntry> statusEntries) {
        this.statusEntries = statusEntries;
    }

    public List<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(List<HashTag> hashTags) {
        this.hashTags = hashTags;
    }
}
