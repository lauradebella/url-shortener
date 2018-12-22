package com.lauradebella.urlshortener.Url;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Builder
@Getter
@Entity
@Data
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Url",
        indexes = {
                @Index(columnList = "longUrl", name = "long_url_index"),
                @Index(columnList = "shortUrlId", name = "short_url_id_index"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "shortUrlId")
        })
public class Url {

    @Id
    private String longUrl;

    private String shortUrl;

    @JsonIgnore
    private String shortUrlId;

    Url(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.shortUrlId = StringUtils.substringAfterLast(shortUrl, "/");
    }
}
