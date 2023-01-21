package com.itstime.allpasstival.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
public class KakaoController {
    private final String url="https://dapi.kakao.com/v2/search/image";
    private final String key = "KakaoAK ebc92a7978c10327d22aff02d08c224c";
    @GetMapping("/kakao")
    public Map callApi(@RequestParam String query){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", key);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("query", query);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Map.class).getBody();
    }
}