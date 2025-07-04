package com.shimi.gsf.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * This class is used to configure the default web MVC settings for the GSF framework.
 * It extends the WebMvcConfigurer interface to customize the message converters.
 * In your project, you can import this configuration class to apply the default settings, e.g.:
 * {@snippet :
 * @SpringBootApplication
 * @Import(GsfDefaultWebMvcConfig.class)
 * public class MyApplication {
 *     public static void main(String[] args) {
 *         SpringApplication.run(MyApplication.class, args);
 *     }
 * }
 * }
 */
@Configuration
public class GsfDefaultWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.forEach(converter -> {
            if(converter instanceof MappingJackson2HttpMessageConverter) {
                // To handle the issue that Snowflake IDs (last 2 or 3 digits) will be truncated by Javascript
                SimpleModule module = new SimpleModule();
                module.addSerializer(Long.class, new ToStringSerializer());

                ((MappingJackson2HttpMessageConverter) converter).getObjectMapper().registerModule(module);
            }
        });
    }
}

