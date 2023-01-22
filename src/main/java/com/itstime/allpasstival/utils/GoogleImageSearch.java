package com.itstime.allpasstival.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class GoogleImageSearch {
    public static Map imageSearch(String q){
        String url = "https://www.googleapis.com/customsearch/v1?key=AIzaSyCfrvm_h8_TCZ1cn_TWXlW2KQmkoHYTxsQ&cx=e4194695ed3714be3";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(url).queryParam("q",q).queryParam("searchType","image").build().encode(StandardCharsets.UTF_8).toUri();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(uri, HttpMethod.GET, entity, Map.class).getBody();
    }
}
