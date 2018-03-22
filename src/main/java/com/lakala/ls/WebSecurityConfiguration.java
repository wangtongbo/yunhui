package com.lakala.ls;

import com.lakala.ls.ms.security.StatelessAuthenticationFilter;
import com.lakala.ls.ms.security.StatelessLoginFilter;
import com.lakala.ls.ms.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    protected SessionRepository<ExpiringSession> sessionRepository;

    @Autowired
    private HttpSessionStrategy httpSessionStrategy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .anonymous()
                .and()
                .servletApi()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/product/queryAddr/**").permitAll()	
                .antMatchers("/product/queryUserProducts").permitAll()	
                .antMatchers("/product/queryProByName").permitAll()	
                .antMatchers("/category/queryCategorys").permitAll()	
                .antMatchers("/category/queryHotCategorys").permitAll()	
                .antMatchers("/category/queryHotProducts").permitAll()	
                .antMatchers("/content/queryUserConts/**").permitAll()	
                .antMatchers("/product/queryProById/**").permitAll()	
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy())
                .and()
                .addFilterBefore(new FilterChainProxy(
                        new DefaultSecurityFilterChain(
                                new AntPathRequestMatcher("/**"),
                                getSessionRepositoryFilter())), ChannelProcessingFilter.class)
                .addFilterBefore(
                        new StatelessAuthenticationFilter(httpSessionStrategy, sessionRepository),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new StatelessLoginFilter("/login",userDetailsService,
                                                    super.authenticationManagerBean()),
                                 UsernamePasswordAuthenticationFilter.class)

               //.httpBasic()
        ;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        config.addExposedHeader("x-auth-token");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public SessionRepositoryFilter getSessionRepositoryFilter(){
        SessionRepositoryFilter sessionRepositoryFilter = new SessionRepositoryFilter(sessionRepository);
        sessionRepositoryFilter.setHttpSessionStrategy(httpSessionStrategy);
        return sessionRepositoryFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
         auth.inMemoryAuthentication().withUser("user").password("password")
         .roles("USER");

        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());

    }


}