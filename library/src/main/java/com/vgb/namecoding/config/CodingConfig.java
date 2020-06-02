package com.vgb.namecoding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
public class CodingConfig {

    @Bean("nameSplitterPattern")
    Pattern nameSplitterPattern() {
        return Pattern.compile(",");
    }
}
