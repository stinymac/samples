/*
 *      (             |"|           !!!       #   ___                             o
 *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
 *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.utils;

import java.util.regex.Pattern;

/**
 * string util
 *
 * @author Mac
 * @create 2018-05-05 16:08
 **/

public abstract class StringUtils {

    public static final Pattern PATTERN_NUMERIC = Pattern.compile("^-?(0|[1-9][0-9]*)(\\.[0-9]+)?$");
    public static final Pattern PATTERN_POSITIVE_INTEGER = Pattern.compile("^[1-9][0-9]*$");


    private StringUtils(){}

    public static boolean isEmpty(String value){
        return value == null || value.trim().length() <= 0;
    }

    public static boolean isNotEmpty(String value){return !isEmpty(value);}


    public static boolean isNumeric(String value){
        if (isEmpty(value))
            return false;

        return PATTERN_NUMERIC.matcher(value).matches();
    }

    public static boolean isPositiveInteger(String value){
        if (isEmpty(value))
            return false;

        return PATTERN_POSITIVE_INTEGER.matcher(value).matches();
    }

    public static boolean isNotPositiveInteger(String value) {
        return !isPositiveInteger(value);
    }
}

