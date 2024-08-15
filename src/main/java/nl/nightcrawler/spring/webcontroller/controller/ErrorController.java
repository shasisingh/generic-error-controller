package nl.nightcrawler.spring.webcontroller.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends AbstractErrorController {

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @GetMapping
    public ResponseEntity<ErrorResponse> errorGet(HttpServletRequest request) {
        return getErrorResponseResponseEntity(request);
    }

    @PostMapping
    public ResponseEntity<ErrorResponse> errorPost(HttpServletRequest request) {
        return getErrorResponseResponseEntity(request);
    }
    private ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(HttpServletRequest request) {
        var errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.of(getALLErrorAttribute()));
        var httpStatus = getStatus(request);
        var errorResponse = getErrorResponse(getMessage(errorAttributes), httpStatus);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
    private static String getMessage(Map<String, Object> errorAttributes) {
        return errorAttributes != null ? errorAttributes.getOrDefault("message", "").toString() : "";
    }

    private static ErrorResponse getErrorResponse(final String message, final HttpStatus httpStatus) {
        return ErrorResponse.builder()
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(message)
                .build();
    }

    @Getter
    @EqualsAndHashCode
    @Builder
    public static final class ErrorResponse {
        private final String timestamp;
        private final int status;
        private final String error;
        private final String message;

    }

    private ErrorAttributeOptions.Include[] getALLErrorAttribute() {
        return ErrorAttributeOptions.Include.values();
    }
}
