package com.lauradebella.urlshortener.Statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {

    private StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }


    @GetMapping(value = "/statistics")
    public StatisticResponse getStatistics() {
        return statisticService.getAllStatistics();
    }
}
