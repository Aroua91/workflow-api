package com.ncq.devstudio.workflows.errors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncq.devstudio.workflows.beans.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/**
 * This {@link RestControllerAdvice} permit to handle {@link ApiException}
 * globally with {@link ExceptionHandler}.
 *
 * @author Aroua Souabni
 */
@RestControllerAdvice(annotations = {RestController.class})
public class ApiErrorControllerAdvice {

    /**
     * Handles {@link ApiException} exceptions.
     *
     * @param request the failing request
     * @param ex instance of {@link ApiException}
     * @return the response with the HTTP status BAD_REQUEST 400 and the body
     * containing the message of the captured {@link Exception}.
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Response> handleError(
            HttpServletRequest request, ApiException ex) {
        Response error = new Response();
        error.setErrorCode(ex.getErrorCode());
        error.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link IllegalArgumentException} exceptions. This exception is
     * thrown when the client input are not valid.
     *
     * @param request the failing request
     * @param ex an {@link IllegalArgumentException}
     * @return the response with the HTTP status BAD_REQUEST 400 and the body
     * containing the message of the captured {@link Exception}.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleError(
            HttpServletRequest request, IllegalArgumentException ex) {
        Response error = new Response();
        error.setMessage(ex.getLocalizedMessage());
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link HttpClientErrorException} exceptions. This exception is
     * thrown when the client input are not valid.
     *
     * @param ex an {@link IllegalArgumentException}
     * @return the response with the HTTP status BAD_REQUEST 400 and the body
     * containing the message of the captured {@link Exception}.
     * @throws IOException on I/O errors
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Response> handleError(
            HttpClientErrorException ex)
            throws IOException {
        String response = ex.getResponseBodyAsString();
        ObjectMapper mapper = new ObjectMapper();
        Response error = mapper.readValue(response, Response.class);

        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    /**
     * Handles {@link HttpServerErrorException} exceptions. This exception is
     * thrown when an error occurs while invoking server.
     *
     * @param request the failing request
     * @param ex an {@link HttpServerErrorException}
     * @return the response with the HTTP status BAD_REQUEST 400 and the body
     * containing the message of the captured {@link Exception}.
     * @throws IOException on I/O error
     */
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Response> handleError(
            HttpServletRequest request, HttpServerErrorException ex)
            throws IOException {
        String response = ex.getResponseBodyAsString();
        ObjectMapper mapper = new ObjectMapper();
        Response error = mapper.readValue(response, Response.class);

        return new ResponseEntity<>(error, ex.getStatusCode());
    }

}
