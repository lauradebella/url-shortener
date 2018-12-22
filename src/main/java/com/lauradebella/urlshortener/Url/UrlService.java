package com.lauradebella.urlshortener.Url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private ShortUrlGenerator shortUrlGenerator;

    private UrlRepository urlRepository;

    @Autowired
    public UrlService(ShortUrlGenerator shortUrlGenerator, UrlRepository urlRepository) {
        this.shortUrlGenerator = shortUrlGenerator;
        this.urlRepository = urlRepository;
    }

    Url shortUrl(String longUrl){

        Url url = urlRepository.findByLongUrl(longUrl);
        if(url == null) {

            String shortUrl = shortUrlGenerator.generate();
            url = new Url(longUrl, shortUrl);

            urlRepository.save(url);
        }
        return url;
    }

    String enlarge(String shortUrlId) throws UrlNotFoundException {

        Url url = urlRepository.findByShortUrlId(shortUrlId);
        if (url == null)
            throw new UrlNotFoundException("Url not found");

        return url.getLongUrl();
    }
}
