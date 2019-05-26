package com.findworkbuddy.mainapiservice.config;

import com.findworkbuddy.mainapiservice.security.TokenAuthenticationFilter;
import com.findworkbuddy.mainapiservice.security.TokenAuthenticationProvider;
import com.findworkbuddy.mainapiservice.security.TokenAuthenticationSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final long EXPIRATION_SECONDS = 1800;

    private TokenAuthenticationProvider authenticationProvider;

    @Autowired
    public JwtSecurityConfig(TokenAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public TokenAuthenticationFilter authenticationTokenFilter() throws Exception {
        TokenAuthenticationFilter filter = new TokenAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(new TokenAuthenticationSuccessHandler());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("**/secured/**").authenticated().and().exceptionHandling()
            .authenticationEntryPoint((request, response, exception) -> response
                .sendError(HttpServletResponse.SC_UNAUTHORIZED, "401 - UNAUTHORIZED"))
            .and().addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}