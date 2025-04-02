package com.basis67.dbkits.generator.service;

import com.basis67.dbkits.generator.exception.CodeGeneratorException;
import com.basis67.dbkits.generator.model.TableMeta;
import freemarker.template.Template;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    @Value("${generator.config-path:classpath:generatorConfig.xml}")
    private String configPath;

    @Value("${generator.base-package}")
    private String codeBasePkg;

    @Value("${generator.author}")
    private String codeAuthor;

    @Value("${generator.output-dir}")
    private String codeOutputDir;

    private freemarker.template.Configuration ftlConfig;

    public CodeGeneratorServiceImpl() {
        this.ftlConfig = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_34);
        ftlConfig.setDefaultEncoding("UTF-8");
        ftlConfig.setClassForTemplateLoading(this.getClass(), "/templates");
    }

    @Override
    public String generateMyBatis() throws CodeGeneratorException {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;

        try {
            File configFile = ResourceUtils.getFile(configPath);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);

            return "代码生成成功！警告信息：" + warnings;
        } catch (Exception e) {
            throw new CodeGeneratorException("生成失败: " + e.getMessage() + " 警告信息：" + warnings, e);
        }
    }

    @Override
    public String generateCodeByTemplateFromTableMeta(String templateName, TableMeta table) throws CodeGeneratorException {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("className", table.getClassName());
        dataModel.put("tableComment", table.getComment());
        dataModel.put("columns", table.getColumns());

        dataModel.put("basePackage", codeBasePkg);
        dataModel.put("author", codeAuthor);

        return this.generateCodeByTemplate(templateName, dataModel);
    }

    @Override
    public String generateCodeByTemplate(String templateName, Map<String, Object> dataModel) throws CodeGeneratorException {
        try {
            Template template = ftlConfig.getTemplate(templateName);
            try (Writer out = new StringWriter()) {
                template.process(dataModel, out);
                out.flush();
                return out.toString();
            }
        } catch (Exception e) {
            throw new CodeGeneratorException("生成失败: " + e.getMessage(), e);
        }
    }
}
