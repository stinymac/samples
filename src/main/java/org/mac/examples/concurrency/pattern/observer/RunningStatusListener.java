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
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-23 16:45
 **/

public class RunningStatusListener implements LifeCycleListener{
    @Override
    public void onEvent(LifeCycleEvent event) {
        System.out.println(event.getThread().getName()+ " running status:"+event.getStatus());
        if (event.getCause() != null) {
            System.out.println(event.getThread().getName()+ " error cause "+event.getCause());
        }
    }
}
