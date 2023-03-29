package com.exam.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.exam.service.impl.UserDetailsServiceImpl;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.ArrayList;
import java.util.List;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
@Configuration
@EnableWebSecurity
public class MySecurityConfig implements WebMvcConfigurer {


	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	private RsaKeyProperties rsaKeys;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
	


	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.csrf(csrf -> csrf.disable()).cors().disable()
				.authorizeRequests(auth -> auth.requestMatchers("/token").permitAll().requestMatchers("/user/").permitAll().requestMatchers("/forgot/**").permitAll().anyRequest().authenticated())
//			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(withDefaults()).exceptionHandling().authenticationEntryPoint(entryPoint);
		http.authenticationProvider(daoAuthenticationProvider());
		http.cors();
		return http.build();
	}
//	
//	@Bean
//	JwtDecoder jwtDecoder() {
//		return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
//	}
//
//	@Bean
//	JwtEncoder jwtEncoder() {
//		JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
//		JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
//		return new NimbusJwtEncoder(jwkSource);
//	}
//
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		final List<GlobalAuthenticationConfigurerAdapter> configurers = new ArrayList<>();

		configurers.add(new GlobalAuthenticationConfigurerAdapter() {
			@Override
			public void configure(AuthenticationManagerBuilder auth) throws Exception {
				auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
			}
		});
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsServiceImpl);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

//		public AuthenticationManager authenticationManagerBean() throws Exception
//		{
//			return super.authenticationManagerBean();
//		}
}
