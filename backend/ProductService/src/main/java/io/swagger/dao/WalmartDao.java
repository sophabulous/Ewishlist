package io.swagger.dao;

import com.EbucketList.api.models.WalmartResponse;
import io.swagger.api.TrackingApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import io.swagger.dao.dao.EcommerceDao;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@Component
public class WalmartDao implements EcommerceDao{

    private final String baseUrl = "https://api.walmartlabs.com/v1/items/";

    private static final Logger log = LoggerFactory.getLogger(TrackingApiController.class);

    @Value("${WALMART_API_KEY}")
    String apiKey;

    public WalmartResponse getItem(String productUrl) throws MalformedURLException {
        String[] components = productUrl.split("/");
        String itemId = components[components.length - 1];
        log.info("url: " + productUrl + " walmartResponse id: " + itemId);

        String urlString  = baseUrl+"/"+itemId+"?format=json&apiKey=" + apiKey;
        log.info("generated url: " + urlString);
        URL url = new URL(urlString);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WalmartResponse> response = restTemplate.getForEntity(url.toString(), WalmartResponse.class);
        WalmartResponse walmartResponse = response.getBody();
        log.info("Response from walmart : " + walmartResponse.toString());
        return walmartResponse;

    }


}

