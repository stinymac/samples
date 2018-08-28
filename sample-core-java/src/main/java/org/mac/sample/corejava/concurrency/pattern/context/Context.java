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
package org.mac.sample.corejava.concurrency.pattern.context;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mac
 * @create 2018-06-06 11:24
 **/

public final class  Context {

    private final static ThreadLocal<Map<String,Object>> STORAGE = new ThreadLocal<>();

    private Context () {

    }

    private static class ContextHolder {
        private  final static Context INSTANCE = new Context();
    }

    public static Context getContext() {
        return ContextHolder.INSTANCE;
    }

    public void put(String key,Object value) {
        Map<String,Object> map = STORAGE.get();
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(key,value);
        STORAGE.set(map);
    }

    public Object get(String key) {
        Map<String,Object> map = STORAGE.get();
        return map.get(key);
    }

    public void clear () {
        Map<String,Object> map = STORAGE.get();
        if (map != null) {
            map.clear();
        }
        STORAGE.set(null);
    }
}
