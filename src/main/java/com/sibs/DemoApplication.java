package com.sibs;

import com.sibs.domain.repository.support.SibsJpaRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(
        repositoryBaseClass = SibsJpaRepository.class
)
@EnableTransactionManagement
@OpenAPIDefinition(info = @Info(title = "Manage Orders API", version = "1.0", description = "Orders and Stock Movement Information"))
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
