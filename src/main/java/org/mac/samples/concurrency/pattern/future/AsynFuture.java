/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.future;

/**
 *
 * @author Mac
 * @create 2018-05-25 20:48
 **/

public class AsynFuture<T> implements Future<T>{

    private volatile T result;
    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (result == null) {
                this.wait();
            }
        }
        return result;
    }

    public void set (T result) {
        synchronized (this) {
           this.result = result;
           this.notifyAll();
        }
    }
}
