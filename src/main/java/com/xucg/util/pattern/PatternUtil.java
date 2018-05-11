package com.xucg.util.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
    /**
     * 全部匹配
     * @param value
     * @param regex
     * @return
     */
    public static boolean isMatch(String value, String regex) {
        if(value == null || value == "") return false;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches() ? true : false;
    }

    /**
     * 可以部分匹配
     * @param value
     * @param regex
     * @return
     */
    public static boolean isFind(String value, String regex) {
        if(value == null || value == "") return false;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.find() ? true : false;
    }

    public static String group(String value, String regex) {
        if(value == null || value == "") return null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }


    //判断是否路径格式，各位为/、/pz/、/pz/little/，限大小写字母、数字和下划线
    public static boolean isPath(String str) {
        String patternStr = "^/([a-zA-Z0-9_]+/)*$";
        return PatternUtil.isMatch(str, patternStr);
    }

    //判断是否页面文件名格式
    public static boolean isPageName(String str) {
        String patternStr = "^[a-zA-Z0-9_${}]+.(html|shtml|php|action|jsp)$";
        return PatternUtil.isMatch(str, patternStr);
    }


    public static void main(String[] args) {

//        System.out.println(isPageName("/eee/index.html"));

//        String sss1 = "fasA";
//        String sss = "31";
        String sss3 = "1";
//        String reg = "(?<0-9A-Za-z>)[^<]+(?=<0-9A-Za-z>)";
        String reg3 = "^(?<word>[a-zA-Z0-9])(\\k<word>)*$";

//        String reg = "[0-9]{" + sss.length() + "}";
//        String reg1 = "[a-zA-Z]{" + sss1.length() + "}";

//        System.out.println(isMatch(sss, reg));
//        System.out.println(isMatch(sss1, reg1));
        System.out.println(isMatch(sss3, reg3));
    }

    /**
     * 为Json字符串的属性值添加引号
     * @param jsonStr
     * @return
     */
    public static String quoteForJson(String jsonStr) {

        Pattern pattern = Pattern.compile(":\\s*([0-9]+\\.*[0-9]*)+([,|\\s|}])+");
        Matcher matcher = pattern.matcher(jsonStr);

        while (matcher.find()) {
            if(matcher.groupCount() >= 2) {
                String s = matcher.group(1);
                if( s != null && !s.isEmpty() )
                    jsonStr = jsonStr.replace(matcher.group(0), ": \"" + s + "\"" + matcher.group(2));
            }
        }

        return jsonStr;

    }



}
