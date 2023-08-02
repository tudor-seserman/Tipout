//package com.tipout.Tipout.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsConfigurationSource;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebSecurity
//public class WebApplicationConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
//
////    private UserDetailsServiceImpl userDetailsService;
////    private BCryptPasswordEncoder bCryptPasswordEncoder;
////
////    public WebSecurity(UserDetailsServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
////        this.userDetailsService = userService;
////        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.cors().and().authorizeRequests()
////                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
////                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
////                // this disables session creation on Spring Security
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////    }
////
////    @Override
////    public void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
////    }
////
////    @Bean
////    CorsConfigurationSource corsConfigurationSource() {
////        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////
////        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
////        source.registerCorsConfiguration("/**", corsConfiguration);
////
////        return source;
////    }
//
//
//    /*
//    // Create spring-managed object to allow the app to access our filter
//    @Bean
//    public AuthenticationFilter authenticationFilter() {
//        return new AuthenticationFilter();
//    }
//
//    // Register the filter with the Spring container
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor( authenticationFilter() );
//    }
//
////    Allows the frontend to access this server for development use
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedMethods("*")
//                        .allowedHeaders("*")
//                        .allowCredentials(false).maxAge(3600);;
//            }
//        };}
//*/
//}
