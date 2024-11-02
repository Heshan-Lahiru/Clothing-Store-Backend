package lk.iuhs.crm.exception;


import lk.iuhs.crm.dao.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerExceptionImpl extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorModel> exceptionmsg (CustomerException ex){

        ErrorModel errorbuild = ErrorModel.builder().msgerror(ex.getMessage()).build();
        return ResponseEntity.ok().body(errorbuild);
    }

}