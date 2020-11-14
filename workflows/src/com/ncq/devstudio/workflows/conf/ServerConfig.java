package com.ncq.devstudio.workflows.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Aroua Souabni
 */
@PropertySource("file:application.properties")
@ConfigurationProperties(prefix = "ncq")
public class ServerConfig {
    
    @Value("${ncq.logo.repo.path}")
    private String logoRepo;

    public String getLogoRepo() {
        return logoRepo;
    }

    public void setLogoRepo(String logoRepo) {
        this.logoRepo = logoRepo;
    }
    
    
}
