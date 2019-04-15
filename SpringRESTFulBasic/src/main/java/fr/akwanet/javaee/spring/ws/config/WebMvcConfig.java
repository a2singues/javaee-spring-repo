package fr.akwanet.javaee.spring.ws.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Allow to configure the web MVC context of the web service
 * @author Assi
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages={"fr.akwanet.javaee.spring.ws.web"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

}
