//package backend.developer.librarymanagementsystem.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@RequiredArgsConstructor
//public class SecurityConfig {
//    private final UserDetailsService userDetailsService;
//
//    private final AuthEntryPointJwt unauthorizedHandler;
//
//    private final AuthTokenFilter authenticationJwtTokenFilter;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
//        return security.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .build();
//
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.authorizeHttpRequests(authorize -> authorize
////                .requestMatchers("/public/**").permitAll()
////                .anyRequest().authenticated()
////                )
////                .httpBasic(httpBasic -> httpBasic
////                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
////        return http.build();
////    }
////
////    @Bean
////    public InMemoryUserDetailsManager userDetailsService() {
////        UserDetails user = User
////                .withUsername("user")
////                .password(passwordEncoder().encode("password"))
////                .roles("USER")
////                .build();
////        return new InMemoryUserDetailsManager(user);
////    }
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder(8);
////    }
//
//    }
//}