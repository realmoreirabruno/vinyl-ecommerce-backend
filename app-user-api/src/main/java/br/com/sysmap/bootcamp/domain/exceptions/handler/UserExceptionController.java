package br.com.sysmap.bootcamp.domain.exceptions.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sysmap.bootcamp.domain.exceptions.NotFoundException;
import br.com.sysmap.bootcamp.domain.exceptions.InvalidCredentialsException;
import br.com.sysmap.bootcamp.domain.exceptions.InvalidPasswordException;
import br.com.sysmap.bootcamp.domain.exceptions.UserAlreadyExistsException;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleExistingAlbum(NotFoundException exception) { 
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleExistingUser(UserAlreadyExistsException exception) { 
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPassword(InvalidPasswordException exception) { 
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException exception) { 
        return ResponseEntity.badRequest().build();
    }
}
