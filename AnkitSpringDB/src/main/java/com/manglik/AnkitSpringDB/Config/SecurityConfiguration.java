package com.manglik.AnkitSpringDB.Config;

import com.manglik.AnkitSpringDB.Filters.JwtFilterRequest;
import com.manglik.AnkitSpringDB.SService.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtFilterRequest jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                /* Spring Security with Roles/UName_PWD etc.
                auth.inMemoryAuthentication()
                .withUser("Ankit").password("test1").roles("ADMIN").and()
                .withUser("Amit").password("test2").roles("USER");

                 */
        auth.userDetailsService(myUserDetailsService);
    }

   @Override
   protected void configure(HttpSecurity httpSecurity) throws Exception {
        /* For AWT Token
       httpSecurity.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll()
               .anyRequest().authenticated().and().sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
         */

       httpSecurity.authorizeRequests()
                  // .antMatchers("/hello").hasRole("USER")
                   .anyRequest()
                   .permitAll()
             //  .fullyAuthenticated()
                   .and().httpBasic();



   }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
