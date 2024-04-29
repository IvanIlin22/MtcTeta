package mts.teta.educationplaform.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import mts.teta.educationplaform.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ApiError> noSuchElementExceptionHandler(NoSuchElementException exception) {
    return new ResponseEntity<>(new ApiError(exception.getMessage(), OffsetDateTime.now()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<List<ApiError>> notValidException(MethodArgumentNotValidException exception) {
   var errors = exception.getBindingResult().getFieldErrors()
        .stream()
        .map(error -> new ApiError(error.getField(), OffsetDateTime.now()))
        .toList();

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
