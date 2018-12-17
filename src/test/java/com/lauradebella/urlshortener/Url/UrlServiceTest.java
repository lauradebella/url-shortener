package com.lauradebella.urlshortener.Url;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlServiceTest {

    @Mock
    private UrlShortIdGenerator urlShortIdGenerator;

    private UrlService service;

    String shortUrl;
    String longUrl;
    Url expectedUrl;

    @Before
    public void setup() {
        initMocks(this);
        this.service = new UrlService(urlShortIdGenerator);

        longUrl = "longUrl.com";
        shortUrl = "short.com";
        expectedUrl = Url.builder()
                .longUrl(longUrl)
                .shortUrl(shortUrl)
                .build();;
    }

    @Test
    public void returnShortedUrl() {
        when(urlShortIdGenerator.generateShorterUrl()).thenReturn(shortUrl);

        Url result = service.shortUrl(longUrl);

        assertThat(result.getShortUrl()).isEqualTo(expectedUrl.getShortUrl());
        assertThat(result.getLongUrl()).isEqualTo(expectedUrl.getLongUrl());
    }
}