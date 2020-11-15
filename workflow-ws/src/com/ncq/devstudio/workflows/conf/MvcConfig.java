package com.ncq.devstudio.workflows.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * This is a spring web configuration. It allows to manage the CORS Filter
 *
 * @author Aroua Souabni
 */
@Configuration
public class MvcConfig {

    /**
     * This method allows to create a {@code CorsFilter} instance. A CORS filter
     * is a filter for Cross Origin HTTP requests. It allows to filter the HTTP
     * requests as configured.
     *
     * The created objects is configured as following:
     * <ul>
     * <li>allowed origin: any</li>
     * <li>allowed headers: any</li>
     * <li>allowed methods: any</li>
     * </ul>
     *
     * @return CORS filter
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source
                = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
