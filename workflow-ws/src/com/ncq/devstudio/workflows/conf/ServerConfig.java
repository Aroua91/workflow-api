package com.ncq.devstudio.workflows.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
    
    @Value("${ncq.page.size}")
    private int pageSize;

    public String getLogoRepo() {
        return logoRepo;
    }

    public void setLogoRepo(String logoRepo) {
        this.logoRepo = logoRepo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
}
