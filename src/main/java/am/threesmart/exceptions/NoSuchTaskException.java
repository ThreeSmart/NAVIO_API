package am.threesmart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "exception.response.status.NO_SUCH_TASK")
public class NoSuchTaskException extends RuntimeException {
}
