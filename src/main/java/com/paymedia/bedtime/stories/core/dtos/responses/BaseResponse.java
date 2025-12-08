package com.paymedia.bedtime.stories.core.dtos.responses;

import com.paymedia.bedtime.stories.core.constants.enums.CommonResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class BaseResponse<T> {
    private int statusCode;
    private T data;
    private String message;

    public BaseResponse(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public BaseResponse(HttpStatus httpStatus, T data) {
        this.statusCode = httpStatus.value();
        this.data = data;
    }

    public BaseResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = null;
    }

    public BaseResponse(HttpStatus httpStatus, String message) {
        this.statusCode = httpStatus.value();
        this.message = message;
        this.data = null;
    }


    public BaseResponse(HttpStatus httpStatus, CommonResponse commonResponse) {
        this.statusCode = httpStatus.value();
        this.message = commonResponse.getValue();
        this.data = null;
    }
}
