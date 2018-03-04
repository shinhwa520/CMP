package com.cmp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import com.cmp.security.AuthSuccessHandler;
import com.cmp.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public AuthSuccessHandler authSuccessHandler() {
		return new AuthSuccessHandler();
	};
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	};

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService userDetailsService = userDetailsService();
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.inMemoryAuthentication().withUser("su").password("su123").roles("SU");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/registration/**").permitAll()
			.antMatchers("/admin/registration/**").hasAnyRole("SU")
			.antMatchers("/admin/role/**").hasAnyRole("SU")
			.antMatchers("/admin/status/**").hasAnyRole("SU")
			.antMatchers("/admin/user/**").hasAnyRole("SU", "ADMIN")
			.antMatchers("/admin/cust/**").hasAnyRole("SU", "ADMIN", "ASST")
			.antMatchers("/channel/**").hasAnyRole("SU", "ADMIN", "ASST", "USER")
			.antMatchers("/index").hasAnyRole("SU", "ADMIN", "ASST", "USER")
			.antMatchers("/share/**").hasAnyRole("SU", "ADMIN", "ASST", "USER")
			.antMatchers("/manage/file/upload").hasAnyRole("SU", "ADMIN", "ASST", "USER")
			.antMatchers("/manage/file/download").hasAnyRole("SU", "ADMIN", "ASST", "USER")
			.antMatchers("/manage/file/downloadProducts").hasAnyRole("SU", "ADMIN", "ASST", "USER")
			.antMatchers("/manage/file/deleteAj").hasAnyRole("SU", "ADMIN", "ASST", "USER")
			.antMatchers("/manage/**").hasAnyRole("SU", "ADMIN", "ASST")
			.antMatchers("/api/**").hasAnyRole("SU")
			.antMatchers("/job/**").hasAnyRole("SU")
//			.anyRequest().hasAnyRole("ADMIN", "USER", "ROLE_USER")
			.and()
		.formLogin().loginPage("/login").permitAll()
			.successHandler(authSuccessHandler())
			.and()
		.logout().permitAll()
			.and()
		.headers()
			//.contentSecurityPolicy("default-src 'self'")	//http://www.ruanyifeng.com/blog/2016/09/csp.html
			//.and()
			.frameOptions()
			.disable()
			.addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS", "ALLOW-FROM http://u5669258.viewer.maka.im"))
			.and()
		.csrf().disable();
	}
}
