package org.yousuowei.usercenter.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security url过滤
 * 
 * @ClassName: MultiHttpSecurityConfig
 * @Description: TODO
 * @author: jie
 * @date: 2014-4-17 下午2:46:02
 */
@Configuration
@EnableWebSecurity
public class MultiHttpSecurityConfig {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password")
				.roles("USER").and().withUser("admin").password("password")
				.roles("USER", "ADMIN");
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends
			WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/api/**").authorizeRequests().anyRequest()
					.hasRole("ADMIN").and().httpBasic();
		}
	}

	@Configuration
	public static class FormLoginWebSecurityConfigurerAdapter extends
			WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().authenticated().and()
					.formLogin();
		}
	}
}
