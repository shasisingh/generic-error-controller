package nl.nightcrawler.spring.webcontroller.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ErrorControllerTest {

    @Test
    void testCheckCustomErrorController() {

        ErrorAttributes errorAttributes = new DefaultErrorAttributes();
        ErrorController errorController = new ErrorController(errorAttributes);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getAttribute("jakarta.servlet.error.status_code")).thenReturn(403);
        when(httpServletRequest.getAttribute("jakarta.servlet.error.exception")).thenReturn(new RuntimeException("No such method"));
        ResponseEntity<ErrorController.ErrorResponse> response = errorController.errorJson(httpServletRequest);
        assertEquals(403, response.getStatusCode().value());
        assertNotNull(response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
        assertNotEquals(0, response.getBody().getStatus());
        assertEquals("Forbidden", response.getBody().getError());
    }

    @Test
    void HttpMediaTypeNotSupportedException() {

        ErrorAttributes errorAttributes = new DefaultErrorAttributes();
        ErrorController errorController = new ErrorController(errorAttributes);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getAttribute("jakarta.servlet.error.status_code")).thenReturn(415);
        when(httpServletRequest.getAttribute("jakarta.servlet.error.exception")).thenReturn(new HttpMediaTypeNotSupportedException("media type is not supported"));
        ResponseEntity<ErrorController.ErrorResponse> response = errorController.errorJson(httpServletRequest);
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
        assertEquals(415, response.getBody().getStatus());
        assertEquals("media type is not supported", response.getBody().getMessage());
    }


}
