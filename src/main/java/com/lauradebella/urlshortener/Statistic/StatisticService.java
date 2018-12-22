package com.lauradebella.urlshortener.Statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StatisticService {
    private StatisticRepository statisticRepository;

    @Autowired
    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    StatisticResponse getAllStatistics(){

        Iterable<Statistic> allStatistics = statisticRepository.findAll();

        int numberOfAccess = getNumberOfAccess(allStatistics);
        int numberOfSuccessfulAccess = statisticRepository.findByWasSuccessful(true).size();
        int numberOfAllShortUrlAccess = statisticRepository.findByEndpoint("short").size();
        int numberOfAllEnlargeUrlAccess = numberOfAccess - numberOfAllShortUrlAccess;

        return new StatisticResponse(numberOfAccess, numberOfAllShortUrlAccess, numberOfAllEnlargeUrlAccess, numberOfSuccessfulAccess);
    }

    public void saveStatistic(String endpoint, Boolean wasSuccessful){
        Statistic statistic = new Statistic(endpoint, wasSuccessful);
        statisticRepository.save(statistic);
    }

    private int getNumberOfAccess(Iterable<Statistic> allStatistics) {
        return ((Collection<?>) allStatistics).size();
    }
}
