package com.lauradebella.urlshortener.Url;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Url {
    private String longUrl;

    private String shortUrl;

}
