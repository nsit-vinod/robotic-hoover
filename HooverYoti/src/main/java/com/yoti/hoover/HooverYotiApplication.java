package com.yoti.hoover;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
/*
 * Spring boot web application boot up class for Yoti Hoover functionality.
 */
@SpringBootApplication
public class HooverYotiApplication  {

	public static void main(String[] args) {
		SpringApplication.run(HooverYotiApplication.class, args);
	}

	/*
	 * Register locale resolver for internationalization.
	 */
	@Bean
	public LocaleResolver localeResolver() {
		//SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	
	
}
