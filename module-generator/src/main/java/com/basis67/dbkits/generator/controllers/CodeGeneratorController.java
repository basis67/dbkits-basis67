package com.basis67.dbkits.generator.controllers;

import com.basis67.dbkits.generator.dto.ApiResult;
import com.basis67.dbkits.generator.dto.GenCodeByTemplateReq;
import com.basis67.dbkits.generator.dto.GenCodeFromTableMetaReq;
import com.basis67.dbkits.generator.exception.CodeGeneratorException;
import com.basis67.dbkits.generator.model.TableMeta;
import com.basis67.dbkits.generator.service.CodeGeneratorService;
import com.basis67.dbkits.generator.service.DbMetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("${openapi.restapi.base-path:/api/}generator")
@Tag(name = "generator", description = "代码生成器 API")
@RequiredArgsConstructor
public class CodeGeneratorController {

    private final CodeGeneratorService codeGeneratorService;

    private final DbMetaService dbMetaService;

    @PostMapping(value = "/runMyBatis")
    @Operation(summary = "运行MyBatis代码生成", description = "运行MyBatis Generator代码生成器")
    @ApiResponses(value = {
            @ApiResponse(description = "成功操作", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResult.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = ApiResult.class))})
    })
    public ApiResult<String> generateCodeByMyBatis() {
        try {
            String result = codeGeneratorService.generateMyBatis();
            return ApiResult.success(result);
        } catch (CodeGeneratorException e) {
            return ApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "代码生成失败: " + e.getMessage());
        }
    }

    @GetMapping(value = "/dbMeta")
    @Operation(summary = "读取数据库元数据", description = "读取MySQL数据库表设计")
    @ApiResponses(value = {
            @ApiResponse(description = "成功", content = {@Content(schema = @Schema(implementation = ApiResult.class))})
    })
    public ApiResult<List<TableMeta>> getDbMeta() {

        try {
            List<TableMeta> result = dbMetaService.readSqlDbMeta();
            return ApiResult.success(result);
        } catch (SQLException e) {
            return ApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "数据库元数据读取失败: " + e.getMessage());
        }
    }

    @PostMapping(value = "/genByTemplate")
    @Operation(summary = "根据模板生成代码", description = "根据指定模板生成代码")
    @ApiResponses(value = {
            @ApiResponse(description = "成功操作", content = {@Content(schema = @Schema(implementation = ApiResult.class))})
    })
    public ApiResult<String> generateCodeByTemplate(
            @Parameter @RequestBody GenCodeByTemplateReq req) {
        try {
            String result = codeGeneratorService.generateCodeByTemplate(req.getTemplateName(), req.getDataModel());
            return ApiResult.success(result);
        } catch (CodeGeneratorException e) {
            return ApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "代码生成失败: " + e.getMessage());
        }
    }

    @PostMapping(value = "/genFromTableMeta")
    @Operation(summary = "根据模板生成代码", description = "根据指定模板生成代码")
    @ApiResponses(value = {
            @ApiResponse(description = "成功操作", content = {@Content(schema = @Schema(implementation = ApiResult.class))})
    })
    public ApiResult<String> generateCodeFromTableMeta(
            @Parameter @RequestBody GenCodeFromTableMetaReq req) {
        try {
            String result = codeGeneratorService.generateCodeByTemplateFromTableMeta(req.getTemplateName(), req.getTableMeta());
            return ApiResult.success(result);
        } catch (CodeGeneratorException e) {
            return ApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "代码生成失败: " + e.getMessage());
        }
    }

}
