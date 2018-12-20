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

    public Url shortUrl(String longUrl){

        Url url = urlRepository.findByLongUrl(longUrl);
        if(url == null) {

            String shortUrl = urlShortIdGenerator.generateShorterUrl();
            url = new Url(longUrl, shortUrl);

            urlRepository.save(url);
        }
        return url;
    }
}
