/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.observer;

/**
 *
 * @author Mac
 * @create 2018-05-23 16:33
 **/

public abstract class ObservableRunnable implements Runnable{
    protected final LifeCycleListener listener;

    protected  ObservableRunnable(final LifeCycleListener listener) {
        this.listener = listener;
    }

    protected synchronized void notifyListener(LifeCycleEvent event){
        listener.onEvent(event);
    }
}
