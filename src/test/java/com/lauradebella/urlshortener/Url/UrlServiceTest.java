package com.lauradebella.urlshortener.Url;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UrlServiceTest {

    @Mock
    private UrlShortIdGenerator urlShortIdGenerator;

    @Mock
    private UrlRepository repository;

    private UrlService service;

    String shortUrl;
    String longUrl;
    Url expectedUrl;

    @Before
    public void setup() {
        initMocks(this);
        this.service = new UrlService(urlShortIdGenerator, repository);

        longUrl = "longUrl.com";
        shortUrl = "short.com/abc";
        expectedUrl = Url.builder()
                .longUrl(longUrl)
                .shortUrl(shortUrl)
                .shortUrlId("abc")
                .build();;
    }

    @Test
    public void returnShortedUrl() {
        when(urlShortIdGenerator.generateShorterUrl()).thenReturn(shortUrl);

        Url result = service.shortUrl(longUrl);

        assertThat(result.getShortUrl()).isEqualTo(expectedUrl.getShortUrl());
        assertThat(result.getLongUrl()).isEqualTo(expectedUrl.getLongUrl());
    }

    @Test
    public void saveShortedUrlOnDatabase() {
        when(urlShortIdGenerator.generateShorterUrl()).thenReturn(shortUrl);

        service.shortUrl(longUrl);

        verify(repository).save(expectedUrl);

    }

    @Test
    public void doNotSaveAgainAnUrlThatAlreadyExists() {
        when(repository.findByLongUrl(longUrl)).thenReturn(expectedUrl);

        Url result = service.shortUrl(longUrl);

        verify(repository).findByLongUrl(longUrl);
        verify(repository, never()).save(any(Url.class));

        Assertions.assertThat(result.getShortUrl()).isEqualTo(expectedUrl.getShortUrl());
        Assertions.assertThat(result.getLongUrl()).isEqualTo(expectedUrl.getLongUrl());
    }

    @Test
    public void enlargeReturnLong() throws UrlNotFoundException {
        String shortUrlId = expectedUrl.getShortUrlId();
        when(repository.findByShortUrlId(shortUrlId)).thenReturn(expectedUrl);

        String enlargedUrl = service.enlarge(shortUrlId);

        Assertions.assertThat(enlargedUrl).isEqualTo(expectedUrl.getLongUrl());
    }

    @Test(expected = UrlNotFoundException.class)
    public void throwsExceptionWhenUrlIsNotFound() {
        String shortUrlId = expectedUrl.getShortUrlId();
        when(repository.findByShortUrlId(shortUrlId)).thenReturn(null);

        service.enlarge(shortUrlId);
    }


}