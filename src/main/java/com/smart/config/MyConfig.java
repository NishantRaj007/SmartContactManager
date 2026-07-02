package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class MyConfig {
	
	@Bean
	public UserDetailsService getUserDetailService() {
		return new UserDetailsServiceImpl();
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
	}

	
	// Configure method...
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//	
//		auth.authenticationProvider(authenticationProvider());
//		
//	}
	
/*	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/signup").permitAll().and()
				.authorizeHttpRequests().requestMatchers("/**")
				.authenticated().and().formLogin().loginPage("/signin").and().csrf().disable().build();
	} */
	
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .requestMatchers("/", "/about", "/signup", "/signin", "/do_register").permitAll()
	            .requestMatchers("/forgot", "/send-otp", "/verify-otp", "/change-password").permitAll()
	            .requestMatchers("/forgot", "/send-otp", "/verify-otp", "/change-password").permitAll()
	            .requestMatchers("/css/**", "/js/**", "/img/**", "/webjars/**").permitAll() // allow static assets
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/signin") 
	            .loginProcessingUrl("/dologin")// Custom login page
	            .defaultSuccessUrl("/user/index", true) // Always go to dashboard after login, ignore saved requests
	            .permitAll() // Allow everyone to access the login page
	        )/*
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/signin?logout")
	            .permitAll()
	        )*/
	        .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity (consider enabling it in production)

	    return http.build();
	}

	
	
	
	
	
	
/*	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/**").permitAll()
				.and().formLogin().and().csrf().disable().build();
	}*/
	
	
//	 @Bean
//	    public RedirectStrategy customRedirectStrategy() {
//	        // Create and return your custom redirect strategy
//	        return new CustomRedirectStrategy();
//	    }
//
//	    private static class CustomRedirectStrategy extends DefaultRedirectStrategy {
//	        // Implement your custom behavior if needed
//	    }
//	
//	protected void configure(HttpSecurity http) throws Exception {
//	     http.authorizeRequests()
//	     .requestMatchers("/admin/**").hasRole("ADMIN")
//	     .requestMatchers("/user/**").hasRole("USER")
//	     .requestMatchers("/**").permitAll().and().formLogin().and().csrf().disable();
//    }
	
	
}