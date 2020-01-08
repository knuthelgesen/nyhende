package no.plasmid.nyhende.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "nyhende")
@EnableConfigurationProperties
@Validated
public class NyhendeConfig {

}
