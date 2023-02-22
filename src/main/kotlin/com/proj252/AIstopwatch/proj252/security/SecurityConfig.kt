package com.proj252.AIstopwatch.proj252.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    public fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
            .loginPage("/login") //로그인이 이뤄질 페이지 명시
            .userInfoEndpoint()
            .userService(oauth2UserService())
            .and()
            .successHandler(oauth2AuthenticationSuccessHandler())


        return http.build()
    }
    @Bean
    fun oauth2UserService(): OAuth2UserService<OAuth2UserRequest, OAuth2User> {
        val delegate = DefaultOAuth2UserService()
        return OAuth2UserService { userRequest ->
            // Retrieve user from OAuth2 provider
            val oAuth2User = delegate.loadUser(userRequest)
            val authorities = oAuth2User.authorities.map { it.authority }
            // Create our own user object with OAuth2 user details
            CustomUserDetails(oAuth2User.name, authorities)
        }
    }

    @Bean
    fun oauth2AuthenticationSuccessHandler(): AuthenticationSuccessHandler {
        return SimpleUrlAuthenticationSuccessHandler("/home")
    }


//    override fun configure(auth: AuthenticationManagerBuilder) {
//        // configure your user store here (e.g., in-memory, JDBC, LDAP)
//    }
}