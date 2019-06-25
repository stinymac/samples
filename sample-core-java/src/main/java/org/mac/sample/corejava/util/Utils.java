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
        try {
            for (T resource:resources){
                if (resource != null)
                    resource.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("close resource exception:",e);
        }
    }
}
