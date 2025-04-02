package com.basis67.dbkits.generator.dto;

import com.basis67.dbkits.generator.model.TableMeta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 从 {@link com.basis67.dbkits.generator.model.TableMeta} 生成代码请求 DTO类
 *
 * @author percent73
 */
@Data
@Schema(description = "从表元数据生成代码请求传输对象")
public class GenCodeFromTableMetaReq implements Serializable {

    @Schema(example = "dto.ftl", description = "模板名称")
    private String templateName;

    @Schema(description = "填入模板的参数")
    private TableMeta tableMeta;

}
