package com.lauradebella.urlshortener.Url;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UrlNotFoundException extends RuntimeException{

    String message;
}
