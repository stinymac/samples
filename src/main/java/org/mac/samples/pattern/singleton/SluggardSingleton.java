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
 * @create 2018-05-21 15:11
 **/

public class SluggardSingleton {
    private static  SluggardSingleton INSTANCE ;

    private SluggardSingleton(){

    }

    public synchronized static SluggardSingleton getInstance(){
        if (null == INSTANCE) {
            INSTANCE = new SluggardSingleton();
        }
        return INSTANCE;
    }
}
