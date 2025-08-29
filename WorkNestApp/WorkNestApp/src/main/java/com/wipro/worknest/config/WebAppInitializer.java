package com.wipro.worknest.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// This class replaces the web.xml file
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // This is for backend configuration (like services, DAOs)
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // This can be used for web-specific beans, but we can leave it null for now
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        // This maps the main Spring servlet to handle all requests ("/")
        return new String[]{"/"};
    }
}