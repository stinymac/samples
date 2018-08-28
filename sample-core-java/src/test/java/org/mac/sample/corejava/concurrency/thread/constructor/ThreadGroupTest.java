/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.thread.constructor;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mac
 * @create 2018-05-06 18:19
 **/

public class ThreadGroupTest {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        ThreadGroup threadGroup = t.getThreadGroup();
        System.out.println(threadGroup);

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (Thread thread:threads){
            System.out.println(thread+" is daemon:"+thread.isDaemon());
        }
    }
}
