package com.elguindy.eazyschoolwebapp;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class EazySchoolWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EazySchoolWebAppApplication.class, args);
    }

}
