package br.com.sysmap.bootcamp.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import br.com.sysmap.bootcamp.domain.exceptions.AlbumAlreadyExistsException;

@ControllerAdvice
public class IntegrationExceptionController {
    @ExceptionHandler(AlbumAlreadyExistsException.class)
    public ResponseEntity<String> handleExistingAlbum(AlbumAlreadyExistsException exception) { 
        return ResponseEntity.badRequest().build();
     }
}
