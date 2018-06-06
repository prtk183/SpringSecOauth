package com.ssecurity.configuration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ssecurity.repository.IUserRepository;
import com.ssecurity.service.UserServiceImpl;
//acting as intercepter
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpecifyConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private IUserRepository repository;
	
	@Override
    @Bean
    public AuthenticationManager    authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
            .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
            .and()
                .httpBasic();
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("sun")
                .password("moon")
                .authorities("ROLE_VIEW")
                .and()
                .withUser("sun")
                .password("moon")
                .authorities( "ROLE_VIEW");
        auth.userDetailsService(userDetailsServiceBean());
    }

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceBean());
	}*/

	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {

		return new UserServiceImpl(repository);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		System.out.println("in configure");
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/secured/**").authenticated().anyRequest().permitAll()
			.and().formLogin()
				.permitAll();
	}
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
		
		//.anyRequest().permitAll() 
		
		.antMatchers("/secured/**").hasAuthority("VIEW").anyRequest().permitAll()
		.and().antMatcher("/secured/**").hasAuthority("VIEW").anyRequest()

	
		//.and().addFilterBefore(myFilter(), BasicAuthenticationFilter.class).httpBasic();
		
		//.antMatchers("/secured/**").hasRole("ADMIN").anyRequest().hasAnyAuthority("VIEW").anyRequest().permitAll()
								.formLogin();
	}*/

/*	@Bean
public MyFilter myFilter() {

		
	return new MyFilter();
}*/
	
}
