package com.lauradebella.urlshortener.Url;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

@Service
public class UrlShortIdGenerator {

     String generateShorterUrl() {
        return RandomStringUtils.randomAlphabetic(10);
    }

}
