/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.pattern.singleton;

/**
 *
 * @author Mac
 * @create 2018-05-21 15:14
 **/

public class StaticInnerClassSingleton {


    private StaticInnerClassSingleton () {

    }

    private static class InstanceHolder {
        private final static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public StaticInnerClassSingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
