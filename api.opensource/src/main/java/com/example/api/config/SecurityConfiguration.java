package com.example.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

@Configuration
public class SecurityConfiguration {

    @Configuration
    @ConditionalOnProperty(name = "keycloak.enabled", havingValue = "false", matchIfMissing = true)
    @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
    protected class SecurityConfigurationDefault extends WebSecurityConfigurerAdapter {

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
            authentication.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN_TOUCAN")
                    .and().withUser("pel").password("{noop}pel").roles("Utilisateurs_DEV_Pelican2");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // desactivation CSRF
            http.csrf().disable().sessionManagement()
                    // use previously declared bean
                    .sessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy())
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    // il faut être authentifié pour toutes les requêtes
                    .and().authorizeRequests().antMatchers("/**").authenticated()
                    // mode basic
                    .and().httpBasic();
        }
    }
}