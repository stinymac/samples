/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

package org.mac.sample.corejava.util;

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


    private StringUtils(){throw new AssertionError("No org.mac.sample.corejava.util.StringUtils instances for you!");}

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

