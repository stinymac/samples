/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.observer;

import java.util.Arrays;

/**
 *
 * @author Mac
 * @create 2018-05-23 17:02
 **/

public class ObserverClient {
    public static void main(String[] args) {
        ObservableThreadTask threadTask = new ObservableThreadTask();
        LifeCycleListener listener = new RunningStatusListener();
        threadTask.concurrencyQuery(Arrays.asList("1","2"),listener);
    }


}
