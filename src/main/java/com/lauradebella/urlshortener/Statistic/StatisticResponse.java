package com.lauradebella.urlshortener.Statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatisticResponse {

    public int numberOfAccess;
    public int numberOfAllShortUrlRequests;
    public int numberOfAllEnlargeUrlRequests;

    public int numberOfSuccessfulAccess;
}
