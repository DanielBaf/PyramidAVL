package Application.Web.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * custom Headers responses
 *
 * @author jefemayoneso
 */
@Getter
public class ApiRequestException extends RuntimeException {

    HttpStatus status;

    public ApiRequestException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
