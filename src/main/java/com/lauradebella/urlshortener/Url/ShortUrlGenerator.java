package com.lauradebella.urlshortener.Url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class ShortUrlGenerator {

    private HttpServletRequest request;
    private int urlShortIdSize;

    @Autowired
    public ShortUrlGenerator(HttpServletRequest request,
                               @Value("${url-short-id-size:4}") String urlShortIdSize) {
        this.request = request;
        this.urlShortIdSize = Integer.valueOf(urlShortIdSize);
    }

    String generate() {
        String randomShortString = generateRandomShortString();
        return String.join("/", generateBaseUrl(), randomShortString);
    }

    private String generateRandomShortString() {
        return RandomStringUtils.randomAlphabetic(urlShortIdSize);
    }

    private String generateBaseUrl() {
        int serverPort = request.getServerPort();
        String scheme = request.getScheme();
        String name = request.getServerName();
        String port = Arrays.asList(80, 443).contains(serverPort) ? "" : String.format(":%s", serverPort);

        return String.format("%s://%s%s", scheme, name, port);
    }

}
