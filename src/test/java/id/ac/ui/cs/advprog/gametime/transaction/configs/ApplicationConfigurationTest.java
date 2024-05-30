package id.ac.ui.cs.advprog.gametime.transaction.configs;

import id.ac.ui.cs.advprog.gametime.transaction.model.User;
import id.ac.ui.cs.advprog.gametime.transaction.repository.UserRepository;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationConfigurationTest {

    @InjectMocks
    private ApplicationConfiguration applicationConfiguration;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserDetailsService() {
        when(userRepository.findByEmail("testUser")).thenReturn(Optional.of(new User()));
        UserDetailsService userDetailsService = applicationConfiguration.userDetailsService();
        assertNotNull(userDetailsService.loadUserByUsername("testUser"));
    }

    @Test
    void testUserDetailsService_UserNotFound() {
        when(userRepository.findByEmail("testUser")).thenReturn(Optional.empty());
        UserDetailsService userDetailsService = applicationConfiguration.userDetailsService();
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("testUser"));
    }

    @Test
    void testPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = applicationConfiguration.passwordEncoder();
        assertNotNull(passwordEncoder);
    }

    @Test
    void testAuthenticationManager() throws Exception {
        AuthenticationManager mockAuthManager = new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) {
                return authentication;
            }
        };

        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(mockAuthManager);
        AuthenticationManager authenticationManager = applicationConfiguration.authenticationManager(authenticationConfiguration);
        assertNotNull(authenticationManager);
    }

    @Test
    void testAuthenticationProviderBeanCreation() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);

        // Create ApplicationConfiguration instance
        ApplicationConfiguration applicationConfigurations = new ApplicationConfiguration(userRepository);

        // Create AuthenticationProvider bean
        AuthenticationProvider authenticationProvider = applicationConfigurations.authenticationProvider();

        // Assert that AuthenticationProvider bean is not null
        assertNotNull(authenticationProvider);
    }
}
