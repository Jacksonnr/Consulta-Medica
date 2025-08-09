package projeto_tomorrow.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecutiryConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()

                        // Endpoint público
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()

                        // Endpoint Admin, Paciente e Médico
                     /*   .requestMatchers(HttpMethod.POST, "/consulta/**").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/consulta/**").hasAnyRole("MEDICO", "PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/medico/**").hasAnyRole("MEDICO", "PACIENTE", "ADMIN")
*/
                        // Endpoints Admin apenas
                        /*.requestMatchers(HttpMethod.PUT, "/consulta/**").hasRole("ADMIN")*/
                        .requestMatchers( "/consulta/**").hasRole("ADMIN")
                    /*  .requestMatchers(HttpMethod.POST, "/medico/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/medico/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/medico/**").hasRole("ADMIN")*/
                        .requestMatchers("/usuario/**").hasRole("ADMIN")
                        .requestMatchers("/paciente/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager autenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
