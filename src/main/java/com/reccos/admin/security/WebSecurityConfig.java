package com.reccos.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.GET, "/api/free").permitAll()
		.antMatchers(HttpMethod.POST, "/api/login").permitAll()
		.anyRequest().authenticated().and().cors();
		
		http.addFilterBefore(new FilterWeb(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
