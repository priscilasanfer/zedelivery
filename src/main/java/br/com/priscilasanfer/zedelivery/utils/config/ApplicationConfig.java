package br.com.priscilasanfer.zedelivery.utils.config;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
