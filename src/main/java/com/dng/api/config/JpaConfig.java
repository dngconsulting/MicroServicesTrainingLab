package com.dng.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dng.api.domain.Registry;
import com.dng.api.repository.RegistryRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = RegistryRepository.class)
@EntityScan(basePackageClasses = Registry.class)
public class JpaConfig {

}
