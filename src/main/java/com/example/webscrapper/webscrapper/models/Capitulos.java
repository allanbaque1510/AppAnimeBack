package com.example.webscrapper.webscrapper.models;

public class Capitulos {
    private Integer id;
    private String name;
    private String url;
    private String img;

    public Capitulos() {
    }

    public Capitulos(Integer id, String name, String url, String img) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.img = img;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
