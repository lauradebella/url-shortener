package com.lauradebella.urlshortener.Url;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@JsonTest
public class UrlRequestTests {

    @Autowired
    private JacksonTester<UrlRequest> json;

    @Test
    public void deserializeJson() throws Exception {
        String content = "{\"longUrl\":\"www.example.com\"}";
        assertThat(json.parse(content)).isEqualTo(new UrlRequest("www.example.com"));
        assertThat(json.parseObject(content).getLongUrl()).isEqualTo("www.example.com");
    }

    @Test
    public void blankUrlShouldReturnMessage() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        UrlRequest url = new UrlRequest("");

        Set<ConstraintViolation<UrlRequest>> validatedSet = validator.validate(url);
        List<String> violations = (List<String>) validatedSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertThat(violations.contains("must not be blank"));
    }
}
