package com.basis67.dbkits.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class ApiResult<T> implements Serializable {

    @Schema(example = "200", description = "响应状态码")
    private Integer code;

    @Schema(example = "ok", description = "响应描述")
    private String message;

    @Schema(description = "响应结果数据")
    private T data;

    @Schema(description = "响应生成时间戳")
    private Long timestamp;

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        result.setData(data);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }

    public static <T> ApiResult<T> error(int code, String message) {
        ApiResult<T> result = new ApiResult<>();
        result.setCode(code);
        result.setMessage(message);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }

}
