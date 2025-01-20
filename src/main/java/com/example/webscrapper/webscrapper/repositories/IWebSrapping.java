package com.example.webscrapper.webscrapper.repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.webscrapper.webscrapper.models.Anime;
import com.example.webscrapper.webscrapper.models.AnimeDetails;
import com.example.webscrapper.webscrapper.models.CapituloDetails;
import com.example.webscrapper.webscrapper.models.Capitulos;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class IWebSrapping {

    protected String UrlBase;

    public IWebSrapping() {
        UrlBase = "https://jkanime.net/";
    }

    @Autowired
    private RestTemplate restTemplate;

    public List<Anime> getInitialAnimeList() {
        List<Anime> ListAnime = new ArrayList<>();
        try {
            Document document = Jsoup.connect(this.UrlBase).get();
            Elements animeList = document.select(".trending__anime .col-lg-3 .anime__item");

            for (Element anime : animeList) {
                String link = anime.select("a").attr("href");
                String img = anime.select(".anime__item__pic").attr("data-setbg");
                String title = anime.select(".anime__item__text h5 a").text();
                String tag_estado = anime.select(".anime__item__text ul li").first().text();
                String tag_categoria = anime.select(".anime__item__text ul .anime").text();
                Anime anim = new Anime(link, img, title, tag_estado, tag_categoria);
                ListAnime.add(anim);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ListAnime;
    }

    public List<Anime> searchListAnime(String param) {
        List<Anime> ListAnime = new ArrayList<>();
        try {
            Document document = Jsoup.connect(this.UrlBase + "/buscar/" + param).get();
            Elements animeList = document.select(".anime__item");

            for (Element anime : animeList) {
                String link = anime.select("a").attr("href");
                String img = anime.select(".anime__item__pic").attr("data-setbg");
                String title = anime.select(".anime__item__text h5 a").text();
                String tag_estado = anime.select(".anime__item__text ul li").first().text();
                String tag_categoria = anime.select(".anime__item__text ul .anime").text();
                Anime anim = new Anime(link, img, title, tag_estado, tag_categoria);
                ListAnime.add(anim);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ListAnime;
    }

    public AnimeDetails getDataAnime(String url, String param_id_paginacion) {
        AnimeDetails detallesAnime = new AnimeDetails();
        try {
            String id_paginacion = "1";
            Document document = Jsoup.connect(url).get();
            String img = document.select(".anime__details__content .anime__details__pic").attr("data-setbg");
            String title = document.select(".anime__details__text h3").text();
            String sipnosis = document.select(".anime__details__text .sinopsis").text();
            Elements chapters = document.select(".anime__pagination a");

            String id_anime = document.select("#guardar-anime").attr("data-anime");

            List<Capitulos> listaCapitulos = new ArrayList<>();
            List<Object> listPagination = new ArrayList<>();

            for (Element element : chapters) {
                String paginacion = element.text();
                String id = element.attr("href").replace("#pag", "");
                Map<String, String> pagina = new HashMap<>();
                pagina.put("id", id);
                pagina.put("label", paginacion);
                listPagination.add(pagina);
            }
            if (param_id_paginacion != null)
                id_paginacion = param_id_paginacion;
            String UrlPaginacion = "https://jkanime.net/ajax/pagination_episodes/" + id_anime + "/"
                    + id_paginacion;
            List<Map<String, String>> responsePagination = this.getDataFromApi(UrlPaginacion);
            for (Map<String, String> map : responsePagination) {
                String capImg = "https://cdn.jkdesu.com/assets/images/animes/video/image_thumb/" + map.get("image");
                String id = map.get("number");
                String name = map.get("title");
                String newUrl = url + id;

                Capitulos capitulo = new Capitulos(Integer.parseInt(id), name, newUrl, capImg);
                listaCapitulos.add(capitulo);
            }
            // for (Integer ini = inicio; ini <= fin; ini++) {
            // newUrl);
            // }
            detallesAnime.setImg(img);
            detallesAnime.setTitulo(title);
            detallesAnime.setResumen(sipnosis);
            detallesAnime.setPaginacion(listPagination);
            detallesAnime.setCapitulos(listaCapitulos);
            detallesAnime.setUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return detallesAnime;
    }

    public CapituloDetails getChapterAnime(String url) {
        CapituloDetails detallesCapitulo = new CapituloDetails();
        try {
            Document documento = Jsoup.connect(url).get();
            String title = documento.select("h1").text();
            Elements scripts = documento.select("script");
            String html = scripts.get(11).html();
            Pattern pattern = Pattern.compile("video\\[(\\d+)]\\s*=\\s*'(.*?)';");
            Matcher matcher = pattern.matcher(html);
            List<String> listaVideo = new ArrayList<>();
            while (matcher.find()) {
                String iframeCode = matcher.group(2);
                Document iframeDoc = Jsoup.parse(iframeCode);
                Element iframeElement = iframeDoc.selectFirst("iframe");
                if (iframeElement != null) {
                    String src = "https://jkanime.net" + iframeElement.attr("src");
                    listaVideo.add(src);
                }
            }
            detallesCapitulo.setTitle(title);
            detallesCapitulo.setLinks(listaVideo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return detallesCapitulo;
    }

    public List<Map<String, String>> getDataFromApi(String url) {
        // Hacer la petición GET
        String response = restTemplate.getForObject(url, String.class);
        // Retornar la respuesta
        List<Map<String, String>> stringToJson = this.convertJsonStringToList(response);
        return stringToJson;
    }

    public List<Map<String, String>> convertJsonStringToList(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convertir el String JSON en una lista de objetos Anime
            List<Map<String, String>> responseList = objectMapper.readValue(jsonString,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));
            return responseList;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // En caso de error
        }
    }

}
