package com.EbucketList.api.dao;

import com.EbucketList.api.models.WalmartResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class WalmartDao {

    private final String baseUrl = "https://api.walmartlabs.com";

    @Value("#{new String('{WALMART_API_KEY}')}")
    String apiKey;

    public WalmartResponse getItem(String productUrl) throws MalformedURLException {
        String[] components = productUrl.split("/");
        String itemId = components[components.length - 1];
        System.out.println("url: " + productUrl + " walmartResponse id: " + itemId);

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("v1")
                .path("items")
                .path(itemId)
                .queryParam("format", "json")
                .queryParam("apiKey", apiKey)
                .build().toUri();

        System.out.println("URI value " + uri.toString());
        URL url = uri.toURL();
        System.out.println("URL value: " + url.toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WalmartResponse> response = restTemplate.getForEntity(url.toString(), WalmartResponse.class);
        WalmartResponse walmartResponse = response.getBody();
        return walmartResponse;

    }


}
