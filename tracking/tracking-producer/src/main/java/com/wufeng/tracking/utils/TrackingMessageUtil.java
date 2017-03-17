package com.wufeng.tracking.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 将消息
 */
public class TrackingMessageUtil {

    /**
     * 收集到的信息用'|'分隔符，所以当信息中含分隔符时需添加转义字符'\'
     * 
     * @param value
     * @return
     */
    public static String escapse(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        value = value.trim().replace("|", "/");
        return value;
    }

    public static void main(String[] args) {// 测试转义的双向转换
        String[] ss = { "abc", "|123", "2|12|33|", "ad|", "|" };

        StringBuilder i = new StringBuilder();
        System.out.println("before:");
        for (String s : ss) {
            System.out.println(s);
            if (i.length() > 0) {
                i.append('|');
            }
            i.append(escapse(s));
        }
        String result = i.toString();
        System.out.println("convert result:" + result);

        String regex = "((([^|])|(\\|))*?)\\|";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(result);
        System.out.println("after:");
        while (matcher.find()) {
            System.out.println("aaa:" + matcher.group(1));
        }

        System.out.println("a|b".replace("|", "\\|"));
    }

}
