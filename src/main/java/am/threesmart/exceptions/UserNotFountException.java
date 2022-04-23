package am.threesmart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "exception.response.status.USER_NOT_FOUND")
public class UserNotFountException extends RuntimeException {
}