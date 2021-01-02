package io.lazyegg.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下划线驼峰互转
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/1 10:47 下午
 */
public class LineTurnsToHumpUtils {


    /**
     * 自动识别，相互转换
     *
     * @param str 待转换字符串
     * @return
     */
    public static String turns(String str) {
        if (str == null) {
            return "";
        }
        if (str.contains("_")) {
            return lineToHump(str);
        } else {
            return humpToLine(str);
        }
    }

    private static final Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static final Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线,效率比上面高
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        if (sb.indexOf("_") == 0) {
           return sb.substring(1, sb.length());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String lineToHump = turns("ParentNoleader");
        System.out.println(lineToHump);// fParentNoLeader
        System.out.println(turns(lineToHump));// f_parent_no_leader
    }
}
