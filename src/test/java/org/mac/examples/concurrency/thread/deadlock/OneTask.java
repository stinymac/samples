/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.thread.deadlock;

/**
 *
 * @author Mac
 * @create 2018-05-09 14:22
 **/

public class OneTask {

    private final  Object oneLock;

    private OtherTask otherTask;

    public OneTask(Object oneLock) {

        this.oneLock = oneLock;

    }

    public void setOtherTask(OtherTask otherTask) {
        this.otherTask = otherTask;
    }

    public void one(){
        synchronized (oneLock) {

            otherTask.m();
            System.out.println(Thread.currentThread()+" hold two lock. ");
        }
    }

    public void m() {
        synchronized (oneLock) {
            //System.out.println("->");
        }
    }
}
