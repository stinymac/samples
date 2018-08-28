/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.pattern.context;

/**
 *
 * @author Mac
 * @create 2018-06-06 13:48
 **/

public class ContextClient {
    public static void main(String[] args) {
        for (int i = 0 ; i < 5; i++) {
            new Thread(new WorkTask()).start();
        }
    }
}
