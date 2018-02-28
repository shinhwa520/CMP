package com.cmp.configuration;

import java.util.Properties;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.cmp.dao.I18nDAO;
import com.cmp.dao.impl.I18nDAOImpl;
import com.cmp.i18n.DatabaseMessageSourceBase;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.cmp")
public class AppConfig implements WebMvcConfigurer {
     
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(1);
        return viewResolver;
    }
    
    /*
     * Create SpringResourceTemplateResolver
     * */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
       SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//       templateResolver.setApplicationContext(applicationContext);
       templateResolver.setPrefix("/WEB-INF/views/");
       templateResolver.setSuffix(".html");
       templateResolver.setOrder(2);
       return templateResolver;
    }
     
    /*
    * Create SpringTemplateEngine
    * */
    @Bean
    public SpringTemplateEngine templateEngine() {
    	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    	templateEngine.setTemplateResolver(templateResolver());
    	templateEngine.setEnableSpringELCompiler(true);
    	return templateEngine;
    }

    /*
    * Register ThymeleafViewResolver
    * */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    	ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    	resolver.setTemplateEngine(templateEngine());
    	registry.viewResolver(resolver);
    }
    
//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        return messageSource;
//    }
    

	@Bean
	public DatabaseMessageSourceBase databaseMessageSourceBase() {
		DatabaseMessageSourceBase messageSource = new DatabaseMessageSourceBase();
//		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean
	public I18nDAO i18nDAO() {
		I18nDAO i18nDAO = new I18nDAOImpl();
//		messageSource.setBasename("messages");
		return i18nDAO;
	}
    
	@Bean
	public JavaMailSenderImpl javaMailSenderImpl(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		//Set gmail email id
		mailSender.setUsername("hector811130@gmail.com");
		//Set gmail email password
		mailSender.setPassword("11261130");
		Properties prop = mailSender.getJavaMailProperties();
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "true");
		return mailSender;
	}
	
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
	}
    
    @Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}	
