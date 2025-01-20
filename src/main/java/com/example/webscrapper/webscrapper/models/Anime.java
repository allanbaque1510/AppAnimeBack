package com.example.webscrapper.webscrapper.models;

public class Anime {
    private String link;
    private String img;
    private String title;
    private String tag_estado;
    private String tag_categoria;

    public Anime() {
    }

    public Anime(String link, String img, String title, String tag_estado, String tag_categoria) {
        this.link = link;
        this.img = img;
        this.title = title;
        this.tag_estado = tag_estado;
        this.tag_categoria = tag_categoria;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag_estado() {
        return tag_estado;
    }

    public void setTag_estado(String tag_estado) {
        this.tag_estado = tag_estado;
    }

    public String getTag_categoria() {
        return tag_categoria;
    }

    public void setTag_categoria(String tag_categoria) {
        this.tag_categoria = tag_categoria;
    }

}
