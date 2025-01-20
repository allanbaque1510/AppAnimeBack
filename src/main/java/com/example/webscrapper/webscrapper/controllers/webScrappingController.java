package com.example.webscrapper.webscrapper.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.webscrapper.webscrapper.models.Anime;
import com.example.webscrapper.webscrapper.models.AnimeDetails;
import com.example.webscrapper.webscrapper.models.CapituloDetails;
import com.example.webscrapper.webscrapper.services.WebScrappingService;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
public class webScrappingController {
    @Autowired
    private WebScrappingService webScrappingService;

    @GetMapping
    public List<Anime> getAllDataInit() {
        return this.webScrappingService.getListAnimeIndex();
    }

    @GetMapping("/{param}")
    public List<Anime> getAllDataInit(@PathVariable String param) {
        return this.webScrappingService.getSearchAnime(param);
    }

    @PostMapping
    public AnimeDetails getAnimeDetails(@RequestBody HashMap<String, String> data) {
        String url = data.get("url");
        String page = data.get("page");
        return this.webScrappingService.getDetailsAnime(url, page);
    }

    @PostMapping("/capitulo")
    public CapituloDetails getCapitulo(@RequestBody HashMap<String, String> data) {
        String url = data.get("url");
        return this.webScrappingService.getCapitulo(url);
    }

}
