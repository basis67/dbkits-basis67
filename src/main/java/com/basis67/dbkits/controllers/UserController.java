package com.basis67.dbkits.controllers;

import com.basis67.dbkits.model.mysql.User;
import com.basis67.dbkits.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${openapi.restapi.base-path:/api/}users")
@Tag(name = "用户管理", description = "用户CRUD操作相关接口")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "获取用户详情", description = "根据用户ID获取用户详细信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户信息"),
            @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    public ResponseEntity<User> getUserById(
            @Parameter(description = "用户ID", required = true) @PathVariable Integer id) {

        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "获取所有用户", description = "获取系统中的所有用户列表")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @Operation(summary = "创建新用户", description = "创建一个新的用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "用户创建成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
        int result = userService.createUser(user);
        if (result > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新用户信息", description = "更新指定用户的信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "用户信息更新成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    public ResponseEntity<Void> updateUser(
            @Parameter(description = "用户ID", required = true) @PathVariable Integer id,
            @Valid @RequestBody User user) {

        user.setId(id);
        int result = userService.updateUser(user);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户", description = "根据ID删除指定用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "用户删除成功"),
            @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "用户ID", required = true) @PathVariable Integer id) {

        int result = userService.deleteUser(id);
        if (result > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
