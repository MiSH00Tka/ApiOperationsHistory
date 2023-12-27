package ru.netology.vikhlyaev.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "operation.processing")
public class OperationProperties {
    private int sleepMilliSeconds;
}