package am.threesmart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "exception.response.status.INVALID_TOKEN")
public class InvalidTokenException extends RuntimeException {
}
