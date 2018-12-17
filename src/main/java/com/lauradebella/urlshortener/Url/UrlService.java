package com.lauradebella.urlshortener.Url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UrlService {

    private UrlShortIdGenerator urlShortIdGenerator;

    @Autowired
    public UrlService(UrlShortIdGenerator urlShortIdGenerator) {
        this.urlShortIdGenerator = urlShortIdGenerator;
    }

    public Url shortUrl(String longUrl) {
        String shortUrl = urlShortIdGenerator.generateShorterUrl();
        return new Url(longUrl, shortUrl);
    }
}
