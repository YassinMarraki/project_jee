package com.project_jee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project_jee.service.UtilisateurConnectService;



@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
	
	@Override  
    public void configure(HttpSecurity http) throws Exception {  
        http  
        	.csrf().disable() 
        	.authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .formLogin().loginPage("/connecter").defaultSuccessUrl("/loginsuccess").failureUrl("/connecter?error=0").usernameParameter("email").passwordParameter("mot_de_passe").permitAll()
            .and()
            .logout().logoutUrl("/deconnecter").logoutSuccessUrl("/").permitAll();
    }  
	

    @Override  
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  
    	auth.authenticationProvider(authenticationProvider()); 
     // Spring Security 5 requires specifying the password storage format
        
    }
    
    
    
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new UtilisateurConnectService();
    }
    
    
    
}