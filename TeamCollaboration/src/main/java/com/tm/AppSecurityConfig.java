package com.tm;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tm.model.Users;
import com.tm.service.TmUserDetailService;
import com.tm.service.TmUserService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private DataSource dataSource;
	
	@Autowired
	private TmUserService tmUserService;
	
	
	@Autowired
	AuthenticationSuccessHandler successHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new TmUserDetailService();
	    }
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
		
	}
	

	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	       auth.authenticationProvider(authProvider());
		
	    }
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
	.csrf().disable()
	.authorizeRequests()
	.antMatchers("/userDashboard").hasRole("User")
	.antMatchers("/adminDashboard").hasRole("Admin")
	//. authenticated ()
    .antMatchers(
            "/",
            "/register",
            "/userregister",
            "/build/**",
            "/dist/**",
            "/plugins/**",
            "/webjars/**").permitAll().antMatchers("/").permitAll()
	.anyRequest().authenticated()
	.and()
	.formLogin()
	.loginPage("/login").permitAll()
	.defaultSuccessUrl("/home", true)
	.and()
	.logout().invalidateHttpSession(true)
	.clearAuthentication(true)
	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	.logoutSuccessUrl("/login").permitAll();
			
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	   /* web
	        .ignoring()
	        .antMatchers("/resources/static/**")
	        .antMatchers("/publics/**");*/
	}
	

	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	
}
