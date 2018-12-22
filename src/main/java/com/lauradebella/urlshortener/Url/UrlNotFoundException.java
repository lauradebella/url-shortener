package com.lauradebella.urlshortener.Url;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class UrlNotFoundException extends RuntimeException{
    String message;
}
