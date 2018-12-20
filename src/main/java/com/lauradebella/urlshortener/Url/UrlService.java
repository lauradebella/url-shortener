package com.lauradebella.urlshortener.Url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UrlService {

    private UrlShortIdGenerator urlShortIdGenerator;

    private UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlShortIdGenerator urlShortIdGenerator, UrlRepository urlRepository) {
        this.urlShortIdGenerator = urlShortIdGenerator;
        this.urlRepository = urlRepository;
    }

    public Url shortUrl(String longUrl) {
        String shortUrl = urlShortIdGenerator.generateShorterUrl();
        Url url = new Url(longUrl, shortUrl);
        urlRepository.save(url);
        return url;
    }
}
