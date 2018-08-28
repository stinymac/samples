/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.pattern.observer;

import java.util.List;

/**
 *
 * @author Mac
 * @create 2018-05-23 17:07
 **/

public class ObservableThreadTask {

    public void concurrencyQuery(List<String> ids, LifeCycleListener listener) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        for (String id : ids) {
            new Thread(new ObservableRunnable(listener) {
                @Override
                public void run() {
                    try {
                        this.notifyListener(new LifeCycleEvent(Status.RUNNING,Thread.currentThread(),null));
                        Thread.sleep(1000);
                        this.notifyListener(new LifeCycleEvent(Status.DONE,Thread.currentThread(),null));
                    } catch (Throwable cause) {
                        this.notifyListener(new LifeCycleEvent(Status.ERROR,Thread.currentThread(),cause));
                    }
                }
            },"Observed Thread-"+id).start();
        }
    }
}
