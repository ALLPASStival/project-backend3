package com.itstime.allpasstival.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class NaverSearchController {
    public static Map callApi(String query) throws UnsupportedEncodingException {
        String url = "https://openapi.naver.com/v1/search/image";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", "xwcuEQ2jAXbOo559_tcA");
        httpHeaders.set("X-Naver-Client-Secret", "ggzjFgn3b3");
        URI uri = UriComponentsBuilder.fromUriString(url).queryParam("query",query).build().encode(StandardCharsets.UTF_8).toUri();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(uri, HttpMethod.GET, entity, Map.class).getBody();
    }

    public static String getImage(String keyword) throws UnsupportedEncodingException {
        Map imageSearch =callApi(keyword);
        ArrayList list = (ArrayList) imageSearch.get("items");
        LinkedHashMap linkedHashMap = (LinkedHashMap) list.get(0);
        return (String) linkedHashMap.get("link");

    }
}
