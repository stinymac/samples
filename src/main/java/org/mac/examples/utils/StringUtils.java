/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mac.examples.utils;

/**
 * string util
 *
 * @author Mac
 * @create 2018-05-05 16:08
 **/

public class StringUtils {

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

