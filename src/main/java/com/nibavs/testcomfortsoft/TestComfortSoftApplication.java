package com.nibavs.testcomfortsoft;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Test app api",
                version = "1.0",
                description = "API documentation for test app."
        )
)
public class TestComfortSoftApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestComfortSoftApplication.class, args);
    }

}
