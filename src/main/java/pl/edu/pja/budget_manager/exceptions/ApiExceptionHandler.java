package pl.edu.pja.budget_manager.exceptions;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({InternalAuthenticationServiceException.class, BadCredentialsException.class})
    public ResponseEntity<String> handleBadCredentialsException(RuntimeException e) {
        return ResponseEntity.status(401).header("Description", "Invalid user credentials").body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder err = new StringBuilder("Invalid request body! Errors:");
        e.getAllErrors().forEach(error -> err.append("\n> ").append(error.getDefaultMessage()));
        return ResponseEntity.status(400).header("Description", "Invalid request body").body(err.toString());
    }

    @ExceptionHandler({JwtException.class, MalformedJwtException.class})
    public ResponseEntity<String> handleJwtException(JwtException e) {
        return ResponseEntity.status(400).header("Description", "Invalid JWT token").build();
    }

    /*@ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e) {

        ProblemDetail problemDetail = null;

        problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(400));
        problemDetail.setProperty("Description", "Invalid request body");

        return problemDetail;
    }*/
}
