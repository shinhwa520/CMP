package com.cmp.configuration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

public class AppInitializer implements WebApplicationInitializer {
	 
    public void onStartup(ServletContext container) throws ServletException {
 
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.register(HibernateConfiguration.class);
        ctx.register(WebSecurityConfig.class);
        container.addListener(new ContextLoaderListener(ctx));
        ctx.setServletContext(container);
        
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        container.addFilter("SiteMeshFilter", new SiteMeshFilter()).addMappingForUrlPatterns(null, false, "*");

 
        servlet.setLoadOnStartup(1);
//        servlet.setMultipartConfig(new MultipartConfigElement(null, 10485760, 1048576, 1048576));
        servlet.addMapping("/");
    }
 
}
