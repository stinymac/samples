/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.active_object;

/**
 *
 * @author Mac
 * @create 2018-06-08 10:06
 **/

public class FutureResult<E> implements Result{

    E resultValue;
    private volatile boolean ready =  false;

    @Override
    public synchronized E getResultValue() {
        while (!ready) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return resultValue;
    }

    public synchronized void setResultValue(E value) {
        resultValue = value;
        this.ready = true;
        this.notifyAll();
    }
}
