package com.ronghuiwl.demotwo.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ronghuiwl.demotwo.serializers.SensitiveSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

//    @Bean
//    public Module sensitiveModule() {
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(String.class, new SensitiveSerializer());
//        return module;
//    }
}