package com.placementcell.config;

import org.hibernate.cache.internal.DisabledCaching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.placementcell.filter.JwtFilter;
import com.placementcell.services.UserInfoServices;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserInfoServices();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
//		return httpSecurity.csrf(csrf->csrf.disable()).cors().and()
//				.authorizeHttpRequests(auth->auth.requestMatchers("/user/update","/company","/company/","/course")
//						.hasRole("USER")
//						.requestMatchers("/company/add","/company/update","/admin/allstudents","/questions/add","/user/update","/company","/company/","/course")
//						.hasRole("ADMIN")
//						.requestMatchers("/user/login","/forgot/otpsend","/forgot/otpreceive")
//						.permitAll()
//						.anyRequest()
//						.authenticated())
//				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authenticationProvider(authenticationProvider())
//				.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
//				.build();	
		return httpSecurity
			    .csrf(csrf->csrf.disable())
			    .cors(Customizer.withDefaults())
			    .authorizeHttpRequests(auth->auth
			        .requestMatchers("/user/login","/forgot/otpsend","/forgot/otpreceive")
			            .permitAll()
			        .requestMatchers("/company/add","/company/update","/admin/allstudents","/questions/add","/company","/company/","/course","/placed/add","/placed/update","/placed/delete")
			            .hasRole("ADMIN")
			        .requestMatchers("/user/update","/placed/getall")
			            .hasAnyRole("ADMIN", "USER")
			        .requestMatchers("/user/update","/company","/company/","/course")
			            .hasRole("USER")
			        .anyRequest().authenticated())
			    .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			    .authenticationProvider(authenticationProvider())
			    .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
			    .build();
	}
	
//	@Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        // Allow all origins, methods, and headers for simplicity.
//        config.addAllowedOrigin("*");
//        config.addAllowedMethod("*");
//        config.addAllowedHeader("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter((CorsConfigurationSource) source);
//    }
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
	{
		return configuration.getAuthenticationManager();
	}
}
