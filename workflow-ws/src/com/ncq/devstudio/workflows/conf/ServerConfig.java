package com.ncq.devstudio.workflows.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * This is a server configuration which is defined in the application file.
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

    /**
     * Returns logo repository path.
     *
     * @return logo repository path
     */
    public String getLogoRepo() {
        return logoRepo;
    }

    /**
     * Sets logo repository path.
     *
     * @param logoRepo logo repository path
     */
    public void setLogoRepo(String logoRepo) {
        this.logoRepo = logoRepo;
    }

    /**
     * Returns page size which is the number of workflows to return by page.
     *
     * @return page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets page size which is the number of workflows to return by page.
     *
     * @param pageSize page size which is the number of workflows to return by
     * page.
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
