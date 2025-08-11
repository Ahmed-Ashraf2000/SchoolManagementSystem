package com.spring.school.config;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Configuration
@Data
@ConfigurationProperties(prefix = "primeschool")
@Validated
public class PropsConfig {
    @Size(max = 25, message = "The property size must not exceed 25 ")
    private Map<String, String> contact;
}
