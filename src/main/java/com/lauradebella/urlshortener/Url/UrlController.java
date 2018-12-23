package com.lauradebella.urlshortener.Url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class UrlController {

    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping(value = "/short",  method= RequestMethod.POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public UrlResponse shortUrl(@Valid @RequestBody UrlRequest request) {
        return new UrlResponse(urlService.shortUrl(request.getLongUrl()));
    }

    @GetMapping(value = "/{shortUrlId}")
    public ModelAndView enlargeUrl(@PathVariable String shortUrlId) {
        return new ModelAndView(String.format("redirect:%s", urlService.enlarge(shortUrlId)));
    }

}
