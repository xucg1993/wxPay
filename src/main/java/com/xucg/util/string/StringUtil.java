package com.xucg.util.string;






import com.xucg.util.pattern.PatternUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public final static String REGEX_MOBILE = "^(13[0-9]||14[0-9]||15[0-9]||18[0-9]||17[0-9])\\d{8}$";
    public final static String REGEX_PHONE_CODE = "^\\d{6}$";
    public final static String NUMBER_CODE = "0123456789";
    public final static String NUMBER_CODE_FIRSTNOZERO = "123456789";
    public final static String STR_CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final static String STR_NUMBER_CODE = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";

    public final static int PWD_LENGTH_BEGIN = 6;
    public final static int PWD_LENGTH_END = 20;


    public static boolean isNullorEmpty(String value) {
        return value == null || value.equals("null") || "".equals(value.trim());
    }

    public static boolean isMobile(String mobile) {
        if (isNullorEmpty(mobile)) return false;
        if (!isLength(mobile, 11)) return false;
        return PatternUtil.isMatch(mobile, REGEX_MOBILE);
    }

    public static boolean isEmail(String email) {
        if (isNullorEmpty(email)) return false;
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static String checkString(String str) {
        if (isNullorEmpty(str)) return "";
        return str.replaceAll("[\\t\\n\\r,]", "");
    }

    //region 计算text的长度（一个中文算两个字符）
    public final static int getLength(String text) {

        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (new String(text.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length / 2;
    }
    //endregion

    /**
     * 防止密码中有中文
     */
    public static boolean isPasswordLegal(String password) {
        if (isNullorEmpty(password)) return false;
        int length = password.length();
        if (password.getBytes().length != length || length < 6 || length > 20) return false;
        return true;
    }

    public static boolean isMobilePasswordLegal(String password) {
        if (isNullorEmpty(password)) return false;
        int length = password.length();
        if (password.getBytes().length != length || !isLength(password, 32)) return false;
        return true;
    }

    public static boolean isLength(String value, int length) {
        if (isNullorEmpty(value)) return false;
        return value.length() == length;
    }

    public static boolean isPhoneCodeLegal(String phoneCode) {
        if (isNullorEmpty(phoneCode) || !isLength(phoneCode, 6)) return false;
        return PatternUtil.isMatch(phoneCode, REGEX_PHONE_CODE);
    }


    /**
     * 判断字符串中是否包含指定字符串
     *
     * @param str      要判断的字符串
     * @param contains 被包含的字符串
     * @return
     */
    public static boolean isContains(String str, String contains) {

        if (isNullorEmpty(str))
            return false;

        Pattern p = Pattern.compile(contains);
        Matcher m = p.matcher(str);

        if (m.find())
            return true;
        else
            return false;
    }


    //region 按正则表达式寻找字符串

    /**
     * 按正则表达式寻找字符串
     *
     * @param sourceStr  原字符串
     * @param patternStr 要寻找的正则表达式
     * @param index      匹配值的索引值，0表示匹配字符串全部
     * @return
     */
    public static List<String> findTextsByPattern(String sourceStr, String patternStr, int index) {

        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(sourceStr);

        List<String> strs = new ArrayList<String>();
        while (matcher.find())
            strs.add(matcher.group(index));

        return strs;
    }

    public static String findTextByPattern(String sourceStr, String patternStr, int index) {

        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(sourceStr);

        while (matcher.find())
            return matcher.group(index);

        return "";
    }
    //endregion

    //region 按正则表达式寻找字符串集合并1对1替换

    /**
     * 按正则表达式寻找字符串集合并1对1替换
     *
     * @param sourceStr   原字符串
     * @param patternStr  要寻找的正则表达式
     * @param replaceStrs 替换的字符串
     * @return
     */
    public static String replaceTextByArry(String sourceStr, String patternStr, List<String> replaceStrs) {

        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(sourceStr);

        StringBuffer sb = new StringBuffer();
        int index = 0;
        while (matcher.find()) {
            if (index > replaceStrs.size() - 1)
                break;
            matcher.appendReplacement(sb, replaceStrs.get(index));
            index++;
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
    //endregion

    //region 按正则表达式寻找字符串集合并全部替换

    /**
     * 按正则表达式寻找字符串集合并1对1替换
     *
     * @param sourceStr  原字符串
     * @param patternStr 要寻找的正则表达式
     * @param replaceStr 替换的字符串
     * @return
     */
    public static String replaceTextAll(String sourceStr, String patternStr, String replaceStr) {

        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(sourceStr);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, replaceStr);
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
    //endregion


    public static String getRandomSixNumbers() {
        return getRandomsNumber(6);
    }

    public static String getRandomFourNumbers() {
        return getRandomsNumber(4);
    }

    public static String getRandomsNumber(int length) {
        StringBuilder sb = new StringBuilder();
        int strLength = NUMBER_CODE.length();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBER_CODE.charAt(random.nextInt(strLength)));
        }
        return sb.toString();
    }

    public static String getRandomsString(int length) {
        StringBuilder sb = new StringBuilder();
        int strLength = STR_CODE.length();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(STR_CODE.charAt(random.nextInt(strLength)));
        }
        return sb.toString();
    }

    //非0开头
//    public static String getRandomsNumberFirstNoZero(int length) {
//        StringBuilder sb = new StringBuilder();
//        int strLength = NUMBER_CODE.length();
//        Random random = new Random();
//        sb.append(NUMBER_CODE_FIRSTNOZERO.charAt(random.nextInt(NUMBER_CODE_FIRSTNOZERO.length())));
//        for (int i = 1; i < length; i++) {
//            sb.append(NUMBER_CODE.charAt(random.nextInt(strLength)));
//        }
//        return sb.toString();
//    }

    public static String getRandomStringAndNumber(int length) {
        StringBuilder sb = new StringBuilder();
        int strLength = STR_NUMBER_CODE.length();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(STR_NUMBER_CODE.charAt(random.nextInt(strLength)));
        }
        return sb.toString();
    }

    //region 截取末端的字符串
    public static String cutEnd(String str, int length) {
        int lenCount = str.length();
        if (lenCount <= length) return str;

        int indexPos = lenCount - length;
        return str.substring(indexPos);
    }
    //endregion

    //region 截取Url中的域名字符串
    public static String getDomainFromUrl(String url) {

        int pos = url.indexOf(":");
        if (pos != -1) url = url.substring(pos + 3);

        pos = url.indexOf("/");
        if (pos != -1) url = url.substring(0, pos);
        return url;
    }
    //endregion

    //region 截取Url中的目录，如/cart/order/
    public static String getDirectoryFromUrl(String url) {

        int pos = url.indexOf(":");
        if (pos != -1) url = url.substring(pos + 3);

        pos = url.indexOf("/");
        if (pos != -1) url = url.substring(pos);
        else return "";

        String patternStr = "[a-zA-Z0-9_]+.(html|shtml|php|action|jsp)$";
        return replaceTextAll(url, patternStr, "");
    }
    //endregion

    //region 截取Url中的文件名，如index.html，cart.action
    public static String getFileNameFromUrl(String url) {

        String patternStr = "[a-zA-Z0-9_]+.(html|shtml|php|action|jsp)$";

        return findTextByPattern(url, patternStr, 0);
    }
    //endregion

    //region 给Url附加http://打头和/结尾
    public static String getUrlWidthHttp(String url) {
        String temp = url.startsWith("http") ? url : "http://" + url;
        return temp.endsWith("/") ? temp : temp + "/";
    }
    //endregion

    //region 删除路径字符串前后的/
    public static String clearSlashOfStartAndEnd(String path) {
        path = path.startsWith("/") ? path.substring(1) : path;
        return path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
    }
    //endregion

    //region 清理路径或Url中的多余斜杠符/
    public static String clearPath(String path) {
        return StringUtil.replaceTextAll(path, "/+", "/");
    }
    //endregion

    public static String arrToString(String[] arr, String separtor) {
        if (arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append(separtor);
        }
        return sb.substring(0, sb.length() - 1);
    }


    public static void main(String[] args) {
        String[] s = new String[]{"sf","wer"};
        String sss = arrToString(s, ",");
        System.out.println(sss);

    }

}
