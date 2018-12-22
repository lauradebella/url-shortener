package com.lauradebella.urlshortener.Statistic;

import com.lauradebella.urlshortener.configuration.MvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MvcConfig.class})
@SpringBootTest
public class StatisticServiceTest {

    private StatisticService service;

    @Mock
    private StatisticRepository statisticRepository;

    List<Statistic> statistics;

    @Before
    public void setUp() {

        this.service = new StatisticService(statisticRepository);

        Statistic statistic = new Statistic("10/12/2020", "short", true);
        statistics =  singletonList(statistic);

        when(statisticRepository.findAll()).thenReturn(statistics);
        when(statisticRepository.findByEndpoint("short")).thenReturn(statistics);
        when(statisticRepository.findByWasSuccessful(true)).thenReturn(statistics);
    }

    @Test
    public void returnNumberOfAccess() {

        StatisticResponse result = service.getAllStatistics();

        int numberOfAccess = statistics.size();
        assertThat(result.getNumberOfAccess()).isEqualTo(numberOfAccess);
    }

    @Test
    public void returnNumberOfSuccessfulAccess() {

        StatisticResponse result = service.getAllStatistics();
        assertThat(result.getNumberOfSuccessfulAccess()).isEqualTo(1);
    }

    @Test
    public void returnNumberOfAccessToEachEndpoint() {

        StatisticResponse result = service.getAllStatistics();
        assertThat(result.getNumberOfAllShortUrlRequests()).isEqualTo(1);
        assertThat(result.getNumberOfAllEnlargeUrlRequests()).isEqualTo(0);
    }

    @Test
    public void saveStatisticsOnDatabase() {
        service.saveStatistic("endpoint", true);
        verify(statisticRepository).save(any());
    }
}