package org.example.finalmd3.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private Object errors;
    private HttpStatus httpStatus;
    public static <T> ApiResponse success(T data, String message, HttpStatus status) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .httpStatus(status)
                .build();
    }
    public static <T> ApiResponse error(String message, Object errors,  HttpStatus status) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .errors(errors)
                .httpStatus(status)
                .build();
    }

}
