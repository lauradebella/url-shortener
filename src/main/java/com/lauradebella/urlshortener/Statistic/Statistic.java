package com.lauradebella.urlshortener.Statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Data
@DynamicUpdate
@AllArgsConstructor
@Getter
@Builder
@Table(name = "Statistic",
        indexes = {
                @Index(columnList = "endpoint", name = "endpoint_index"),
                @Index(columnList = "wasSuccessful", name = "was_successful_index"),
        })
public class Statistic {

    @Id
    private String dateTime;

    private String endpoint;

    private Boolean wasSuccessful;

}