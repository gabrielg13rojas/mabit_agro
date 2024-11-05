package py.com.mabit.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import py.com.mabit.entidades.Usuarios;
import py.com.mabit.repositorios.UsuarioRepositorio;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login","/login/resetear-password").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error=true")
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")//BORRAR COOKIES PARA OBLIGAR AL USU A INICIAR NUEVAMENTE
            )
            .csrf(csrf -> csrf.disable()) // Para simplicidad, en producción habilitar
        	.sessionManagement(session -> session
                .maximumSessions(1) // Limitar a 1 sesión activa por usuario
                .sessionRegistry(sessionRegistry()) // Registro de sesiones
                .expiredUrl("/login?sessionExpired=true") // Redirigir si la sesión expira
            );

        return http.build();
    }
    
    @Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Autowired
            private UsuarioRepositorio usuarioRepository;

            @Override
            public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
                Usuarios usuario = usuarioRepository.findByCorreo(correo)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
                return User.builder()
                	.accountLocked(usuario.getBloqueado())
                    .username(usuario.getCorreo())
                    .password(usuario.getContrasenha())
                    .roles(usuario.getPrivilegio().getDescripcion())
                    .build();
            }
        };
    }
}

// Repositorio de Usuario
