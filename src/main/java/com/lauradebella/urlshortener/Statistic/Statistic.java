package com.lauradebella.urlshortener.Statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "Statistic",
        indexes = {
                @Index(columnList = "endpoint", name = "endpoint_index"),
                @Index(columnList = "wasSuccessful", name = "was_successful_index"),
        })
class Statistic {
    @Id
    private String dateTime;

    private String endpoint;

    private Boolean wasSuccessful;

     Statistic(String endpoint, Boolean wasSuccessful) {
        this.dateTime = LocalDateTime.now().toString();
        this.endpoint = endpoint;
        this.wasSuccessful = wasSuccessful;
    }
}