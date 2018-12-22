package com.lauradebella.urlshortener.Url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
        @NotBlank String longUrl = request.getLongUrl();
        return new UrlResponse(urlService.shortUrl(longUrl));
    }

    @GetMapping(value = "/{shortUrlId}")
    public ModelAndView enlargeUrl(@PathVariable String shortUrlId) {
        return new ModelAndView(String.format("redirect:%s", urlService.enlarge(shortUrlId)));
    }
}
