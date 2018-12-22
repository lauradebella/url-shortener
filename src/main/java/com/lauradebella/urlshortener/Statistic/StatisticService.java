package com.lauradebella.urlshortener.Statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public StatisticResponse getAllStatistics(){

        Iterable<Statistic> allStatistics = statisticRepository.findAll();

        int numberOfAccess = getNumberOfAccess(allStatistics);
        int numberOfSuccessfulAccess = getNumberOfSuccessfulAccess(allStatistics);

        return new StatisticResponse(numberOfAccess, numberOfSuccessfulAccess);
    }

    private int getNumberOfAccess(Iterable<Statistic> allStatistics) {
        return ((Collection<?>) allStatistics).size();
    }

    private int getNumberOfSuccessfulAccess(Iterable<Statistic> allStatistics) {
        List<Statistic> successfulAccess = StreamSupport.stream(allStatistics.spliterator(), false)
                .filter(statistic -> statistic.getWasSuccessful() == true)
                .collect(Collectors.toList());

        return successfulAccess.size();
    }
}
