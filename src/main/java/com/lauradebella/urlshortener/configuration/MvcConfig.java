package com.lauradebella.urlshortener.configuration;

import com.lauradebella.urlshortener.Statistic.StatisticService;
import com.lauradebella.urlshortener.Url.UrlControllerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@SpringBootApplication(scanBasePackages={"com.lauradebella.urlshortener"})
public class MvcConfig implements WebMvcConfigurer {

    StatisticService statisticService;

    @Autowired
    public MvcConfig(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    public MvcConfig() {
        super();
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);

        return bean;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new UrlControllerInterceptor(statisticService));
    }
}
