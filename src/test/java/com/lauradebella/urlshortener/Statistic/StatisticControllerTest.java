package com.lauradebella.urlshortener.Statistic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(StatisticController.class)
public class StatisticControllerTest {

    @MockBean
    private StatisticService statisticService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void returnStatistics() throws Exception {

        StatisticResponse response = new StatisticResponse(4,3, 1,2);

        when(statisticService.getAllStatistics()).thenReturn(response);

        mockMvc.perform(get("/statistics"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.numberOfAccess").value("4"))
                .andExpect(jsonPath("$.numberOfSuccessfulAccess").value("2"))
                .andExpect(jsonPath("$.numberOfAllShortUrlRequests").value("3"))
                .andExpect(jsonPath("$.numberOfAllEnlargeUrlRequests").value("1"));
    }

}