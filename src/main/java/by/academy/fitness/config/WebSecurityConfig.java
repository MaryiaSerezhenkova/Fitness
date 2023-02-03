package by.academy.fitness.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import by.academy.fitness.domain.entity.User.ROLE;
import by.academy.fitness.security.filters.CorsFilter;
import by.academy.fitness.security.jwt.AuthEntryPointJwt;
import by.academy.fitness.security.jwt.AuthTokenFilter;
import by.academy.fitness.service.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;

	private final AuthEntryPointJwt unauthorizedHandler;
	private final CorsFilter corsFilter;

	@Autowired
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AuthEntryPointJwt unauthorizedHandler,
			CorsFilter corsFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.unauthorizedHandler = unauthorizedHandler;
		this.corsFilter = corsFilter;
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService((UserDetailsService) userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
				.antMatchers("/api/v1/users/signin").permitAll()
				.antMatchers("/api/v1/users/signup").permitAll()
				.antMatchers("/api/v1/users/verify/**").permitAll()
				.antMatchers("/api/v1/users/me").hasAnyRole(ROLE.ADMIN.name(), ROLE.USER.name())
				.antMatchers("/api/v1/users/**").hasRole(ROLE.ADMIN.name())
				.antMatchers("/api/v1/audit/**").hasRole(ROLE.ADMIN.name())
				.anyRequest().authenticated();

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	protected SkipPathRequestMatcher skipPathRequestMatcher() throws Exception {
		List<String> urls = new ArrayList<>();
		urls.add("/signup");
		urls.add("/signin");
		return new SkipPathRequestMatcher(urls, "/api/v1/users");
	}
}