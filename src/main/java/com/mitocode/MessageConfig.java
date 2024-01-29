package com.mitocode;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig {

    // TODO: 29/01/24 @Bean -> Es una anotacion como cualquier otro, solo q se le puede agregar mas parametros
    // TODO: 29/01/24 MessageConfig -> Esto sirve para que spring lo reconosca y lo pueda leer
    // TODO: 29/01/24 messageSource -> Cargar los properties. es para cargar los archivos que se llaman messages de la ruta del "ClassPath" es decir de la ruta del resources mas info en https://www.baeldung.com/spring-custom-validation-message-source
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    // TODO: 29/01/24 localeResolver -> Para decirle cual es el idioma por defecto que va asumir al cargar (ROOT) si quieres poner un udioma en particular solo cambiarlo por otro
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ROOT);
        return slr;
    }

    // TODO: 29/01/24 getValidator -> Para resolver las variables del mensaje. Para que reconosca las llaves de lo properties del menssages, de lo contrario no lo encuentra, para ello en el Bean debes agregarle {nombres.size} etc por cada atributo y haga match con los properties
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }



}
