/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.utils;

/**
 * string util
 *
 * @author Mac
 * @create 2018-05-05 16:08
 **/

public abstract class StringUtils {

    private StringUtils(){}

    public static boolean isEmpty(String str){
        return str == null || str.trim().length() <= 0;
    }

    public static boolean isNotEmpty(String str){return !isEmpty(str);}


    public static boolean isNumeric(String str){
        if (isEmpty(str))
            return false;
        final String numeric = "^-?(0|[1-9][0-9]*)(\\.[0-9]+)?$";
        return str.matches(numeric);
    }

    public static boolean isPositiveInteger(String str){
        if (isEmpty(str))
            return false;
        final String positiveInteger = "^[1-9][0-9]*$";
        return str.matches(positiveInteger);
    }

    public static boolean isNotPositiveInteger(String str) {
        return !isPositiveInteger(str);
    }
}

