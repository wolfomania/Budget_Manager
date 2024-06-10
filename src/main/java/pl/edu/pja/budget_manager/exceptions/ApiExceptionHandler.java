package pl.edu.pja.budget_manager.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(401).header("Description", "Bad user credentials").body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder err = new StringBuilder("Invalid request body! Errors:");
        e.getAllErrors().forEach(error -> err.append("\n> ").append(error.getDefaultMessage()));
        return ResponseEntity.status(400).header("Description", "Invalid request body").body(err.toString());
    }
}
