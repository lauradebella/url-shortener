package com.lauradebella.urlshortener.Url;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lauradebella.urlshortener.Statistic.StatisticService;
import com.lauradebella.urlshortener.configuration.MvcConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UrlController.class)
@ContextConfiguration(classes = { MvcConfig.class})
public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UrlService urlService;

    @MockBean
    private StatisticService statisticService;

    @Test
    public void shortUrl() throws Exception {
        UrlRequest request = new UrlRequest("http://www.longurl.com");
        Url response = Url.builder()
                .longUrl("http://www.longurl.com")
                .shortUrl("localhost/abc")
                .shortUrlId("abc")
                .build();

        given(this.urlService.shortUrl("http://www.longurl.com")).willReturn(response);

        mockMvc.perform(post("/short").contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.longUrl").value("http://www.longurl.com"))
                .andExpect(jsonPath("$.shortUrl").value("localhost/abc"));

        verify(statisticService).saveStatistic(any(), any());
    }

    @Test
    public void enlargeAndRedirectToUrl() throws Exception {
        given(this.urlService.enlarge("shortUrlId")).willReturn("longUrl.com");
        mockMvc.perform(get("/shortUrlId"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("longUrl.com"));

        verify(statisticService).saveStatistic(any(), any());
    }

    @Test
    public void urlNotFound() throws Exception {
        when(this.urlService.enlarge(any())).thenThrow(new UrlNotFoundException("message"));
        mockMvc.perform(get("/shortUrlId"))
                .andExpect(status().isNotFound());
    }
}