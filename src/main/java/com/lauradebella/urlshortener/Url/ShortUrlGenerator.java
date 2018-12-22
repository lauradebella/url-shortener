package com.lauradebella.urlshortener.Url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class ShortUrlGenerator {

    private HttpServletRequest request;

    @Autowired
    public ShortUrlGenerator(HttpServletRequest request) {
        this.request = request;
    }

    String generate() {
        String randomShortString = generateRandomShortString();
        return String.join("/", generateBaseUrl(), randomShortString);
    }

    private String generateRandomShortString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    private String generateBaseUrl() {
        int serverPort = request.getServerPort();
        String scheme = request.getScheme();
        String name = request.getServerName();
        String port = Arrays.asList(80, 443).contains(serverPort) ? "" : String.format(":%s", serverPort);

        return String.format("%s://%s%s", scheme, name, port);
    }

}
