package id.ac.ui.cs.advprog.gametime.transaction.exceptions;

import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ProblemDetail;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleSecurityException() {
        Exception exception = new Exception("Test exception");
        ProblemDetail response = globalExceptionHandler.handleSecurityException(exception);
        assertEquals("Test exception", response.getDetail());
    }

    @Test
    public void testHandleExpiredJwtException() {
        ExpiredJwtException exception = new ExpiredJwtException(null, null, "Test exception");
        ProblemDetail response = globalExceptionHandler.handleSecurityException(exception);
        assertEquals("Test exception", response.getDetail());
    }

    @Test
    public void testHandleSignatureException() {
        SignatureException exception = new SignatureException("Test exception");
        ProblemDetail response = globalExceptionHandler.handleSecurityException(exception);
        assertEquals("Test exception", response.getDetail());
    }

    @Test
    public void testHandleAccessDeniedException() {
        AccessDeniedException exception = new AccessDeniedException("Test exception");
        ProblemDetail response = globalExceptionHandler.handleSecurityException(exception);
        assertEquals("Test exception", response.getDetail());
    }

    @Test
    public void testHandleAccountStatusException() {
        AccountStatusException exception = new AccountStatusException("Test exception") {};
        ProblemDetail response = globalExceptionHandler.handleSecurityException(exception);
        assertEquals("Test exception", response.getDetail());
    }

    @Test
    public void testHandleBadCredentialsException() {
        BadCredentialsException exception = new BadCredentialsException("Test exception");
        ProblemDetail response = globalExceptionHandler.handleSecurityException(exception);
        assertEquals("Test exception", response.getDetail());
    }
}
