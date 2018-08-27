/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.util;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @author Mac
 * @create 2018-06-07 15:43
 **/

public final class Utils {
    private Utils(){}

    public static <T extends Closeable>void close(T... resources) {
        if (resources == null || resources.length <= 0) {
            return;
        }
        for (T resource:resources){
            if (resource != null) {
                try {
                    resource.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                    throw new RuntimeException("close resource exception:",e);
                }
            }
        }
    }
}
