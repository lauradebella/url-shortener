package com.lauradebella.urlshortener.Url;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class UrlResponse {

    private String longUrl;
    private String shortUrl;

    @JsonCreator
    UrlResponse(Url url) {
        this.longUrl = url.getLongUrl();
        this.shortUrl = url.getShortUrl();
    }

}
