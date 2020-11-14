package com.ncq.devstudio.workflows;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This class is the entry point to launch a server.
 *
 * @author Aroua Souabni
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ncq.devstudio.workflows"})
@EnableJpaRepositories(basePackages = {"com.ncq.devstudio.workflows.repositories"})
public class Application {

    /**
     * Entry point to launch a server.
     *
     * @param args optional arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).registerShutdownHook();
    }

}
