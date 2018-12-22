package com.lauradebella.urlshortener.Url;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {
    Url findByLongUrl(String longUrl);
    Url findByShortUrlId(String shortUrlId);
}