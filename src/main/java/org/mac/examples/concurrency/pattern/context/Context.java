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
package org.mac.examples.concurrency.pattern.context;
/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
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
