package no.plasmid.nyhende.config;

import no.plasmid.nyhende.filter.OrientDBTransactionFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    @Order(value=1)
    public Filter orientDBTransactionFilter() {
        return new OrientDBTransactionFilter();
    }

}
