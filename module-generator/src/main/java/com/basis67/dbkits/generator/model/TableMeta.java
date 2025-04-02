package com.basis67.dbkits.generator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 表元数据 模型
 *
 * @author percent73
 */
@Data
@Schema(description = "数据库表元数据")
public class TableMeta {
    /**
     * 数据库表名称
     */
    @Schema(description = "数据库表名称")
    private String tableName;

    /**
     * 转换后的Java类名（大驼峰格式）
     */
    @Schema(description = "转换后的Java类名（大驼峰格式）")
    private String className;

    /**
     * 表注释（用于生成Swagger描述）
     */
    private String comment;

    /**
     * 列元数据集合
     */
    private List<ColumnMeta> columns;

    /**
     * 主键列（用于生成查询方法）
     */
    private ColumnMeta primaryKey;
}