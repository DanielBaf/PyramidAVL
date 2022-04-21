/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Web.Exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author jefemayoneso
 */
@ControllerAdvice
public class ApiExceptionHandler {

    // 400 error
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        // create payload containing exception details
        ApiException apiException = new ApiException(e.getMessage(), e.getStatus(), ZonedDateTime.now(ZoneId.of("Z")));
        // return entity response
        return new ResponseEntity<>(apiException, e.getStatus());
    }
}
