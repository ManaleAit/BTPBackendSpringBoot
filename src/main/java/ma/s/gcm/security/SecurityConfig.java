package ma.s.gcm.security;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({KeycloakSpringBootConfigResolver.class})
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter{
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
	keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
	auth.authenticationProvider(keycloakAuthenticationProvider);
}

/**
 * Defines the session authentication strategy.
 */
@Bean
@Override
protected NullAuthenticatedSessionStrategy sessionAuthenticationStrategy() {
    return new NullAuthenticatedSessionStrategy();
}

@Override
protected void configure(HttpSecurity http) throws Exception
{
    super.configure(http);
    http
            .authorizeRequests()
            .antMatchers("/fonction/**").hasAnyRole("assistant","responsable")
            .antMatchers("/ville/**").hasAnyRole("assistant","responsable")
            .antMatchers("/jourFerie/**").hasAnyRole("assistant","responsable")
            .antMatchers("/Appeloffre/**").hasAnyRole("assistant","responsable")
            .antMatchers("/caution/**").hasAnyRole("assistant","responsable")
            .antMatchers("/conge/**").hasAnyRole("assistant","responsable")
            .antMatchers("/document/**").hasAnyRole("assistant","responsable")
            .antMatchers("/Employee/**").hasAnyRole("assistant","responsable")
            .antMatchers("/etabli/**").hasAnyRole("assistant","responsable")
            .antMatchers("/facture/**").hasAnyRole("assistant","responsable")
            .antMatchers("/intervenant/**").hasAnyRole("assistant","responsable")
            .antMatchers("/MaitreOuvrage/**").hasAnyRole("assistant","responsable")
            .antMatchers("/Marche/**").hasAnyRole("assistant","responsable")
            .antMatchers("/prestation/**").hasAnyRole("assistant","responsable")
            .antMatchers("/projet/**").hasAnyRole("assistant","responsable")
            .antMatchers("/typeDocument/**").hasAnyRole("assistant","responsable")
            .anyRequest().authenticated();
}

/*
    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);


    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }


    @Bean
    public FilterRegistrationBean keycloakAuthenticationProcessingFilterRegistrationBean(KeycloakAuthenticationProcessingFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("Configuring keycloak {}", "Web Security !");
        super.configure(http);
       // http.authorizeRequests().antMatchers("/fonction/**").hasAnyRole("assistant");
 
        http.cors()
        .and()
        .csrf()
        .disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .sessionAuthenticationStrategy(sessionAuthenticationStrategy())
        .and()
        .authorizeRequests()
        .antMatchers("/fonction/**").hasRole("assistant")
        .anyRequest().permitAll();
                

    }
    
    
    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }*/
}