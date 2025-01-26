package com.example.webscrapper.webscrapper.models;

import java.util.List;

public class SearchData {
    private String next;
    private String prev;
    private List<Anime> data;

    public SearchData() {
    }

    public SearchData(String next, String prev, List<Anime> data) {
        this.next = next;
        this.prev = prev;
        this.data = data;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public List<Anime> getData() {
        return data;
    }

    public void setData(List<Anime> data) {
        this.data = data;
    }

}
