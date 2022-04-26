package com.exadel.hotel.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelmapper() {
        return new ModelMapper();
    }
}
