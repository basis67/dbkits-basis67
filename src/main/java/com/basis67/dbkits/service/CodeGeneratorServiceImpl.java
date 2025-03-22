package com.basis67.dbkits.service;

import com.basis67.dbkits.exception.CodeGeneratorException;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    @Value("${generator.config-path:classpath:generatorConfig.xml}")
    private String configPath;

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
}
