package com.example.webscrapper.webscrapper.models;

import java.util.List;

public class CapituloDetails {
    private String title;
    private List<String> links;

    public CapituloDetails() {
    }

    public CapituloDetails(String title, List<String> links) {
        this.title = title;
        this.links = links;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

}
