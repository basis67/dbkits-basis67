package com.basis67.dbkits.controllers;

import com.basis67.dbkits.dto.ApiResult;
import com.basis67.dbkits.exception.CodeGeneratorException;
import com.basis67.dbkits.service.CodeGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${openapi.restapi.base-path:/api/}generator")
@Tag(name = "generator", description = "代码生成器 API")
@RequiredArgsConstructor
public class CodeGeneratorController {

    private final CodeGeneratorService codeGeneratorService;

    @PostMapping(value = "/runMyBatis")
    @Operation(summary = "运行MyBatis代码生成", description = "运行MyBatis Generator代码生成器")
    @ApiResponses(value = {
            @ApiResponse(description = "成功操作", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResult.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = ApiResult.class)) })
    })
    public ApiResult<String> generateCode() {
        try {
            String result = codeGeneratorService.generateMyBatis();
            return ApiResult.success(result);
        } catch (CodeGeneratorException e) {
            return ApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "代码生成失败: " + e.getMessage());
        }
    }
}
