package com.basis67.dbkits.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "hello", description = "hello API")

@RestController
public class HelloController {

    @Operation(summary = "获取hello消息", tags = { "hello" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功操作", content = @Content )
    })

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
