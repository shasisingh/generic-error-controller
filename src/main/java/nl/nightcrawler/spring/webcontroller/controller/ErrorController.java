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
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends AbstractErrorController {

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    public ResponseEntity<ErrorResponse> errorJson(HttpServletRequest request) {
        return getErrorResponseResponseEntity(request);
    }

    private ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(HttpServletRequest request) {
        var errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.of(getALLErrorAttribute()));
        var httpStatus = getStatus(request);
        var errorResponse = getErrorResponse(errorAttributes, httpStatus);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private static String getMessage(Map<String, Object> errorAttributes) {
        return Objects.nonNull(errorAttributes) ? errorAttributes.getOrDefault("message", "").toString() : "";
    }

    private static String getPath(Map<String, Object> errorAttributes) {
        return Objects.nonNull(errorAttributes) ? errorAttributes.getOrDefault("path", "").toString() : "";
    }

    private static ErrorResponse getErrorResponse(Map<String, Object> errorAttributes, final HttpStatus httpStatus) {
        return ErrorResponse.builder()
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .status(httpStatus.value())
                .path(getPath(errorAttributes))
                .message(getMessage(errorAttributes))
                .error(httpStatus.getReasonPhrase())
                .build();
    }

    @Getter
    @EqualsAndHashCode
    @Builder
    public static final class ErrorResponse {
        private final int status;
        private final String timestamp;
        private final String error;
        private final String message;
        private final String path;

    }

    private ErrorAttributeOptions.Include[] getALLErrorAttribute() {
        return ErrorAttributeOptions.Include.values();
    }
}
