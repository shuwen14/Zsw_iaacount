package com.example.zsw_iaccount.util;

/**
 * Created by 赵舒文 on 2018-3-19.
 */

public class RegexUtils {

    private RegexUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 匹配手机号码的正则表达式，只校验 1 开头并且是 11 位数的。
     */
    public static final String PHONE_NUMBER_REGEX = "^1(3|4|5|7|8)[0-9]\\d{8}$";

    /**
     * 匹配邮箱的正则表达式
     * "www."可省略不写
     */
    public static final String EMAIL_REGEX = "^(www\\.)?\\w+@\\w+(\\.\\w+)+$";

    /**
     * 匹配密码的正则表达式(不能以数字开头,6-15位,并且是包含数字和字母的组合)
     */
    public static final String PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

    /**
     * 匹配汉字的正则表达式，个数限制为一个或多个
     */
    public static final String CHINESE_REGEX = "^[\u4e00-\u9f5a]+$";

    /**
     * 匹配正整数的正则表达式，个数限制为一个或多个
     */
    public static final String POSITIVE_INTEGER_REGEX = "^\\d+$";

    /**
     * 校验手机号是否正确
     * @param phone 手机号
     * @return true: 正确 false: 不正确
     */
    public static boolean checkPhone(String phone) {
        return phone.matches(PHONE_NUMBER_REGEX);
    }

    /**
     * 匹配给定的字符串是否是一个邮箱账号，"www."可省略不写
     *
     * @param string 给定的字符串
     * @return true：是
     */
    public static boolean isEmail(String string) {
        return string.matches(EMAIL_REGEX);
    }


    /**
     * 校验密码是否输入正确
     * @param password 密码
     * @return true: 正确 false：不正确
     */
    public static boolean checkPassword(String password){
        return password.matches(PASSWORD);
    }

    /**
     * 匹配给定的字符串是否全部由汉字组成
     *
     * @param string 给定的字符串
     * @return true：是
     */
    public static boolean isChinese(String string) {
        return string.matches(CHINESE_REGEX);
    }

    /**
     * 验证给定的字符串是否全部由正整数组成
     *
     * @param string 给定的字符串
     * @return true：是
     */
    public static boolean isPositiveInteger(String string) {
        return string.matches(POSITIVE_INTEGER_REGEX);
    }
}
