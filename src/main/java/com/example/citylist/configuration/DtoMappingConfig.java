package com.example.citylist.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoMappingConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
