package br.com.treinaweb.cleancodesolid.exceptions.handles;

import br.com.treinaweb.cleancodesolid.dtos.outputs.ErrorResponse;
import br.com.treinaweb.cleancodesolid.exceptions.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidacaoException exception){
        ErrorResponse response = new ErrorResponse();

        response.setTimestamp(LocalDateTime.now());
        response.setStatus("BAD REQUEST");
        response.setMensagem(exception.getLocalizedMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
