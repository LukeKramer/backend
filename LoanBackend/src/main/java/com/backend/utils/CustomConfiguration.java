package com.backend.utils;


import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lukekramer on 22/08/2016.
 */
//@Configuration
public class CustomConfiguration {
    /* @Bean

    public HttpMessageConverters customConverter()
     {
         Collection<HttpMessageConverter<?>> messageConverter = new ArrayList<>();

         GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
         messageConverter.add(gsonHttpMessageConverter);

         return new HttpMessageConverters(true,messageConverter);

     }*/
}
