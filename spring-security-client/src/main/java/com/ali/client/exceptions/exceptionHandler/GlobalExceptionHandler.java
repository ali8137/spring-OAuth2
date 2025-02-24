//package com.dailycodebuffer.client.exceptions.exceptionHandler;
//
//import com.dailycodebuffer.client.exceptions.UserNotFoundException;
//import com.dailycodebuffer.client.exceptions.errorResponse.ErrorResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.LocalDateTime;
//
//@ControllerAdvice
//@Slf4j
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException exception) {
//
////        for other developers:
//        log.error("user not found {}", exception.getMessage());
//
//
////        for clients:
//        ErrorResponse errorResponse
//                = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
//}
