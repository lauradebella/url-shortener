package com.lauradebella.urlshortener.Url;

import com.lauradebella.urlshortener.Statistic.StatisticService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
public class UrlControllerInterceptor extends HandlerInterceptorAdapter {

    private static final int HTTP_OK = 200;
    private static final int HTTP_REDIRECT = 302;
    private StatisticService statisticService;

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {

        if( !getPath(request).equals("statistics")) {
            statisticService.saveStatistic(getPath(request), wasSuccessful(response));
        }
    }

    private Boolean wasSuccessful(HttpServletResponse response){
        return response.getStatus() == HTTP_OK || response.getStatus() == HTTP_REDIRECT;
    }

    private String getPath(HttpServletRequest request){
        return StringUtils.substringAfterLast(request.getRequestURL().toString(), "/");
    }

}