/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.active_object;

/**
 *
 * @author Mac
 * @create 2018-06-08 15:29
 **/

public final class ActiveObjectFactory {
    private ActiveObjectFactory(){}

    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        ActiveObjectProxy proxy = new ActiveObjectProxy(servant,queue);


        return proxy;
    }
}
