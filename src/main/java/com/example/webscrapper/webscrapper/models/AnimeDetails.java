package com.example.webscrapper.webscrapper.models;

import java.util.List;

public class AnimeDetails {
    private String img;
    private String url;
    private String titulo;
    private String resumen;
    private List<Capitulos> capitulos;
    private List<Object> paginacion;

    public AnimeDetails() {
    }

    public AnimeDetails(String img, String url, String titulo, String resumen, List<Capitulos> capitulos,
            List<Object> paginacion) {
        this.img = img;
        this.url = url;

        this.titulo = titulo;
        this.resumen = resumen;
        this.capitulos = capitulos;
        this.paginacion = paginacion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public List<Capitulos> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulos> capitulos) {
        this.capitulos = capitulos;
    }

    public List<Object> getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(List<Object> paginacion) {
        this.paginacion = paginacion;
    }

}
