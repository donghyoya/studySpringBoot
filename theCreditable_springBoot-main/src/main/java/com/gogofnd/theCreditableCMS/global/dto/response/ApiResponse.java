package com.gogofnd.theCreditableCMS.global.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private ApiHeaderResponse header;
    private T data;
    public ApiResponse(T data){
        this.header = new ApiHeaderResponse();
        this.data = data;
    }


}

