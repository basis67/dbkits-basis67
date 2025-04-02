package com.basis67.dbkits.generator.util;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * 元数据转换工具类
 */
public class JdbcTypeUtil {
    private static final Map<Integer, String> TYPE_MAPPING = new HashMap<>();

    static {
        TYPE_MAPPING.put(Types.VARCHAR, "java.lang.String");
        TYPE_MAPPING.put(Types.BIGINT, "java.lang.Long");
        TYPE_MAPPING.put(Types.INTEGER, "java.lang.Integer");
        TYPE_MAPPING.put(Types.TIMESTAMP, "java.util.Date");
        TYPE_MAPPING.put(Types.DECIMAL, "java.math.BigDecimal");
        TYPE_MAPPING.put(Types.BIT, "java.lang.Boolean");
    }

    /**
     * 转换JDBC类型为Java类型
     */
    public static String parse(int jdbcType) {
        return TYPE_MAPPING.getOrDefault(jdbcType, "java.lang.Object");
    }

    /**
     * 简化类型名称（去掉包名前缀）
     */
    public static String simpleType(String fullTypeName) {
        if (fullTypeName == null) return "";
        int lastDotIndex = fullTypeName.lastIndexOf('.');
        return lastDotIndex > 0
                ? fullTypeName.substring(lastDotIndex + 1)
                : fullTypeName;
    }
}
