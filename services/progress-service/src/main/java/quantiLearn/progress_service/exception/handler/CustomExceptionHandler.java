package quantiLearn.progress_service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import quantiLearn.progress_service.exception.ExceptionResponse;
import quantiLearn.progress_service.exception.exceptions.CustomException;
import quantiLearn.progress_service.exception.exceptions.CustomNotFoundException;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> customException(CustomException exc){
        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setMessage(exc.getMessage());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setTimestamp(new Date());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> customNotFoundException(CustomNotFoundException exc){
        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setMessage(exc.getMessage());
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setTimestamp(new Date());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
}
