package com.example.dinesh;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
@Controller
public class RestfulWebServicesApplication {

	@Autowired
	private MessageSource messageSource;
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	@RequestMapping("/welcome")
	@ResponseBody
	String welcomeGreetings()
	{
		return "Welcome to this example!!!!";
	}
	
	@RequestMapping("/welcome-internationalized")
	@ResponseBody
	public String welcomeGreetingsInternationalized(@RequestHeader(name="Accept-Language",required=false) Locale locale)
	{
		return messageSource.getMessage("good.morning.message",null, locale);
	}

	/*public String welcomeGreetingsInternationalized()
	{
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}*/
	@Bean
	public LocaleResolver localeResolver()
	{
		/*SessionLocaleResolver sessionLocaleResolver=new SessionLocaleResolver();*/
		AcceptHeaderLocaleResolver sessionLocaleResolver=new AcceptHeaderLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		return sessionLocaleResolver;
	}
	
	@Bean
	public ResourceBundleMessageSource bundleMessageSource()
	{
		ResourceBundleMessageSource msgSource= new ResourceBundleMessageSource();
		msgSource.setBasename("message");
		return msgSource;
	}
}
