/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.pattern.observer;

/**
 *
 * @author Mac
 * @create 2018-05-23 16:11
 **/

public class ObserverClient {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer observer = new SimpleObserver();

        subject.attach(observer);
        for (int i = 0; i < 5; i++) {
            subject.setStatus(i+1);
        }
    }
}
