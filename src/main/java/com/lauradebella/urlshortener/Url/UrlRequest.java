package com.lauradebella.urlshortener.Url;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Data
@Getter
public class UrlRequest {

    @NotBlank
    String longUrl;

    @JsonCreator
    public UrlRequest(@JsonProperty("longUrl") String longUrl) {
        this.longUrl = longUrl;
    }

}
