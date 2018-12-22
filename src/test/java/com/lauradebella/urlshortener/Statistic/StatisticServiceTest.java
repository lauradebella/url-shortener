package com.lauradebella.urlshortener.Statistic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatisticServiceTest {

    private StatisticService service;

    @Mock
    private StatisticRepository statisticRepository;

    Iterable<Statistic> statistics;

    @Before
    public void setUp() {

        this.service = new StatisticService(statisticRepository);

        Statistic statistic = new Statistic("10/12/2020", "GET", true);
        statistics =  singletonList(statistic);

        when(statisticRepository.findAll()).thenReturn(statistics);
    }

    @Test
    public void returnNumberOfAccess() {

        StatisticResponse result = service.getAllStatistics();

        int numberOfAccess = ((Collection<?>) statistics).size();
        assertThat(result.getNumberOfAccess()).isEqualTo(numberOfAccess);
    }

    @Test
    public void returnNumberOfSuccessfulAccess() {

        StatisticResponse result = service.getAllStatistics();
        assertThat(result.getNumberOfSuccessfulAccess()).isEqualTo(1);
    }
}