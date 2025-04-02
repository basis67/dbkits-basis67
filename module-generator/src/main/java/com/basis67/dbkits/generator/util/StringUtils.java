package com.basis67.dbkits.generator.util;

public class StringUtils {

    /**
     * 下划线转驼峰命名法
     * @param name 原始字符串
     * @param firstCap 首字母是否大写
     * @return 驼峰命名字符串
     */
    public static String toCamelCase(String name, boolean firstCap) {
        if (name == null || name.isEmpty()) {
            return "";
        }

        // 统一转为小写处理
        String lowerName = name.toLowerCase();
        StringBuilder result = new StringBuilder();
        boolean nextUpper = firstCap;

        for (int i = 0; i < lowerName.length(); i++) {
            char c = lowerName.charAt(i);

            if (c == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(c));
                    nextUpper = false;
                } else {
                    // 处理连续大写字母的情况（如：HELLO_WORLD -> helloWorld）
                    if (i == 0 && !firstCap) {
                        result.append(Character.toLowerCase(c));
                    } else {
                        result.append(c);
                    }
                }
            }
        }

        // 处理原始字符串以下划线结尾的情况
        return result.toString();
    }

    /**
     * 增强版驼峰转换（处理全大写单词）
     * 示例：USER_NAME -> userName / UserName
     *       my_NAME_ID -> myNameId / MyNameId
     */
    public static String enhancedCamelCase(String name, boolean firstCap) {
        String baseName = toCamelCase(name, firstCap);

        // 处理全大写单词的转换（如：ID -> Id）
        if (baseName.length() > 1) {
            char[] chars = baseName.toCharArray();
            for (int i = 0; i < chars.length - 1; i++) {
                if (Character.isUpperCase(chars[i])
                        && Character.isUpperCase(chars[i+1])) {
                    chars[i+1] = Character.toLowerCase(chars[i+1]);
                }
            }
            return new String(chars);
        }
        return baseName;
    }
}