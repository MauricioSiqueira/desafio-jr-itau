package mauricio.itaujr.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import mauricio.itaujr.dto.ErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<ErroResponse> handleBusinessException(
            BussinessException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred",
                request,
                List.of(ex.getClass().getSimpleName())
        );
    }

    private ResponseEntity<ErroResponse> buildResponse(
            HttpStatus status,
            String message,
            HttpServletRequest request,
            List<String> details
    ){
        ErroResponse errorResponse = new ErroResponse(
                OffsetDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                details
        ) {
        };
        return ResponseEntity.status(status).body(errorResponse);
    }
}
