package com.accenture.bankledger.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_KEY = "error";

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequestException(
            Exception exception
    ) {
        BadRequestException raisedException = (BadRequestException) exception;
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                raisedException.getErrorId(),
                raisedException.getErrorMessage(),
                raisedException.getErrorDetails()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValid(
            Exception exception
    ) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid request body.",
                getErrorDetails(exception)
        );

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(org.springframework.dao.DataAccessException.class)
    public ResponseEntity<ApiErrorResponse> handleDataAccessException(
            Exception exception) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "A database error occurred.",
                Map.of(ERROR_KEY, exception.getMessage())
        );
        return new ResponseEntity<>(errorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(java.time.format.DateTimeParseException.class)
    public ResponseEntity<ApiErrorResponse> handleDateTimeParseException(
            Exception exception) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid date format. Use dd/MM/yyyy.",
                Map.of(ERROR_KEY, exception.getMessage())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(
            Exception exception) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred.",
                Map.of(ERROR_KEY, exception.getMessage())
        );
        return new ResponseEntity<>(errorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> getErrorDetails(Exception exception) {
        Map<String, Object> errorDetails = new HashMap<>();
        List<FieldError> fieldErrors =
                ((MethodArgumentNotValidException) exception)
                        .getBindingResult()
                        .getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            errorDetails.put(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            );
        }

        return errorDetails;
    }
}
