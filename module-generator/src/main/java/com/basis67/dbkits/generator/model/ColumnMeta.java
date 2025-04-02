package com.basis67.dbkits.generator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 列元数据 模型
 */
@Data
@Schema(description = "数据库表列元数据")
public class ColumnMeta {
    /**
     * 数据库列名
     */
    private String columnName;

    /**
     * Java字段名（小驼峰格式）
     */
    private String javaField;

    /**
     * Java类型（完整类名）
     * 示例：java.lang.String
     */
    private String javaType;

    /**
     * 列注释（用于生成Swagger描述）
     */
    private String comment;

    /**
     * 是否可为空（用于生成校验注解）
     */
    private boolean nullable;

    /**
     * 是否为主键（影响Mapper方法选择）
     */
    private boolean primaryKey;

    /**
     * JDBC类型（对应java.sql.Types）
     */
    private int jdbcType;

    /**
     * 列长度（用于生成@Size校验）
     */
    private int length;
}