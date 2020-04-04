package com.choza.pequenines.vscovid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan
@EnableTransactionManagement
@EnableCaching
@EntityScan(basePackageClasses = {VsCovid19Application.class, Jsr310JpaConverters.class})
public class VsCovid19Application {

	public static void main(String[] args) {
		SpringApplication.run(VsCovid19Application.class, args);
	}
	
	@EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST,
                            "/login")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, 
                    		"/ping")
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

    }

}
