package com.lauradebella.urlshortener.Url;

import com.lauradebella.urlshortener.Statistic.StatisticService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UrlControllerInterceptorTest {

    private final String STATISTICS_URL = "localpath/statistics";
    private final String SHORT_URL = "localpath/short";

    private UrlControllerInterceptor interceptor;

    @Mock
    private StatisticService statisticService;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    Object handler;

    @Mock
    Exception ex;

    StringBuffer buffer;

    @Before
    public void setup() {

        buffer = new StringBuffer();

        interceptor = new UrlControllerInterceptor(statisticService);
    }

    @Test
    public void requestsFromStatisticEndpointShouldNotBeSaved() throws Exception {

        buffer.append(STATISTICS_URL);
        when(request.getRequestURL()).thenReturn(buffer);
        interceptor.afterCompletion(request, response, handler, ex);

        verify(statisticService, never()).saveStatistic(any(), any());
    }

    @Test
    public void requestsFromOtherEndpointsShouldBeSaved() throws Exception {

        buffer.append(SHORT_URL);
        when(request.getRequestURL()).thenReturn(buffer);
        interceptor.afterCompletion(request, response, handler, ex);

        verify(statisticService).saveStatistic(any(), any());
    }
}