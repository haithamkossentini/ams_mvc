package com.sip.ams.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

public class WebMvcConfig implements WebMvcConfigurer {
	/*	@Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }*/
		
		@Bean(name = "localeResolver")
		public LocaleResolver getLocaleResolver()  {
			CookieLocaleResolver resolver= new CookieLocaleResolver();
			 resolver.setDefaultLocale(new Locale("en"));
			resolver.setCookieDomain("myAppLocaleCookie");
			// 60 minutes 
			resolver.setCookieMaxAge(60*60); 
			return resolver;
		} 
		
		@Bean(name = "messageSource")
		public MessageSource getMessageResource()  {
			ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();
			
			// Read i18n/messages_xxx.properties file.
			// For example: i18n/messages_en.properties
			messageResource.setBasename("classpath:message/messages");
			messageResource.setDefaultEncoding("UTF-8");
			return messageResource;
		}
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
			localeInterceptor.setParamName("lang");
			
			
			registry.addInterceptor(localeInterceptor);
		}
	}