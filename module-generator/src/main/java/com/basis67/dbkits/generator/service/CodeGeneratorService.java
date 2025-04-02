package com.basis67.dbkits.generator.service;


import com.basis67.dbkits.generator.exception.CodeGeneratorException;
import com.basis67.dbkits.generator.model.TableMeta;

import java.util.Map;

public interface CodeGeneratorService {

    /**
     * 运行MyBatis Generator生成器
     * @return 生成器运行结果
     * @throws CodeGeneratorException 代码生成器异常
     */
    String generateMyBatis() throws CodeGeneratorException;

    /**
     * 从指定模板生成代码，填入数据库元数据
     * @param templateName 模板名称
     * @param tableMeta 数据库元数据
     * @return 生成的代码
     */
    String generateCodeByTemplateFromTableMeta(String templateName, TableMeta tableMeta) throws CodeGeneratorException;

    /**
     * 从指定模板生成代码
     * @param templateName 模板名称
     * @param dataModel 数据模型
     * @return 根据模板生成的代码
     */
    String generateCodeByTemplate(String templateName, Map<String, Object> dataModel) throws CodeGeneratorException;

}