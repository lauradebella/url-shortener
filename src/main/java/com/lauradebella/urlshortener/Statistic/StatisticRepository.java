package com.lauradebella.urlshortener.Statistic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface StatisticRepository extends CrudRepository<Statistic, Long> {
    List<Statistic> findByWasSuccessful(Boolean wasSuccssful);
    List<Statistic> findByEndpoint(String endpoint);
}