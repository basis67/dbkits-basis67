/**
 * ${tableComment} DTO类
 *
 * @author ${author}
 */
@Data
@Schema(description = "${tableComment}数据传输对象")
public class ${className}Dto {
    <#list columns as column>

    @Schema(description = "${column.comment!''}"<#if !column.nullable>, required = true</#if>)
    <#if !column.nullable>
    @NotBlank(message = "${column.comment}不能为空")
    private ${column.javaType} ${column.javaField};
    </#if>
    <#if column.nullable>
    private ${column.javaType} ${column.javaField};
    </#if>

    </#list>
}