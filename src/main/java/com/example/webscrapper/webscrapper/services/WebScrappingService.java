package com.example.webscrapper.webscrapper.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webscrapper.webscrapper.models.Anime;
import com.example.webscrapper.webscrapper.models.AnimeDetails;
import com.example.webscrapper.webscrapper.models.CapituloDetails;
import com.example.webscrapper.webscrapper.repositories.IWebSrapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebScrappingService {

    @Autowired
    private IWebSrapping webSrappingRepository;

    public List<Anime> getListAnimeIndex() {
        return this.webSrappingRepository.getInitialAnimeList();
    }

    public AnimeDetails getDetailsAnime(String url, String page) {
        return this.webSrappingRepository.getDataAnime(url, page);
    }

    public CapituloDetails getCapitulo(String url) {
        return this.webSrappingRepository.getChapterAnime(url);
    }

    public List<Anime> getSearchAnime(String param) {
        return this.webSrappingRepository.searchListAnime(param);
    }
}
