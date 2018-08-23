/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.pattern.singleton;

/**
 *
 * @author Mac
 * @create 2018-05-21 15:16
 **/

public class EnumSingleton {
    private EnumSingleton (){

    }

    private enum Holder {
        INSTANCE;

        private final EnumSingleton instance;

        Holder () {
            instance = new EnumSingleton();
        }

        private EnumSingleton getInstance() {
            return instance;
        }
    }

    public EnumSingleton getInstance() {
        return Holder.INSTANCE.getInstance();
    }
}
