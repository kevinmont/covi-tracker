package com.choza.pequenines.vscovid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan
@EnableTransactionManagement
@EnableCaching
@EntityScan(basePackageClasses = { VsCovid19Application.class, Jsr310JpaConverters.class })
public class VsCovid19Application {

	public static void main(String[] args) {
		SpringApplication.run(VsCovid19Application.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private CorsFilter corsFilter;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers(HttpMethod.POST, "/login", "/user").permitAll()
					.antMatchers(HttpMethod.GET, "/ping").permitAll().anyRequest().authenticated().and()
					.addFilter(corsFilter).csrf()
					.disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}

	}
	
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("OPTIONS");
		configuration.addAllowedMethod("HEAD");
		configuration.addAllowedMethod("GET");
		configuration.addAllowedMethod("PUT");
		configuration.addAllowedMethod("POST");
		configuration.addAllowedMethod("DELETE");
		configuration.addAllowedMethod("PATCH");
		configuration.addExposedHeader("Authorization");
		source.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(source);
	}

}
