package dev.nils.spring.ai.exception;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
    @ExceptionHandler(Exception.class)
    public Mono<ApiResponse<>> handleException(Exception e) {
        return Mono.just(new ApiResponse<>(false, e.getMessage(), null));
    }
     */
}