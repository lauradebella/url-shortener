package com.lauradebella.urlshortener.Url;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ShortUrlGeneratorTest {

    private MockHttpServletRequest request;

    private ShortUrlGenerator generator;

    private int SERVER_PORT = 123;
    private String SERVER_NAME = "name";
    private String SCHEME = "scheme";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        request = new MockHttpServletRequest();
        request.setServerName(SERVER_NAME);
        request.setScheme(SCHEME);
        request.setServerPort(SERVER_PORT);

        generator = new ShortUrlGenerator(request, "4");
    }

    @Test
    public void returnCompleteShortedUrl(){
        String shortUrl = generator.generate();

        assertTrue(shortUrl.contains(SCHEME + "://" + SERVER_NAME + ":" + SERVER_PORT));
    }

}