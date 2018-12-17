 /*
  *      (             |"|           !!!       #   ___                             o
  *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
  *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  *
  * 虽不能至,心向往之。(Although I can't, my heart is longing for it.)
  *
  *
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
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
