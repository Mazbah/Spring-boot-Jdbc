package com.mazbah.springsecurityjdbc.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@EnableWebSecurity
class SecurityConfig(private var dataSource: DataSource) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder){
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, enabled"
                                    + "from users "
                                    + "where username = ?")
            .authoritiesByUsernameQuery("select username, authority"
                                    + "from authorities"
                                    + "where username = ?")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/admin").hasRole("ADMIN")
            .antMatchers("/user").hasAnyRole("ADMIN","USER")
            .antMatchers("/").permitAll()
            .and().formLogin()
    }

    @Bean
    fun passWordEncoder(): PasswordEncoder{
        return NoOpPasswordEncoder.getInstance()
    }
}