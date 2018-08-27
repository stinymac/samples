/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.thread.deadlock;

/**
 *
 * @author Mac
 * @create 2018-05-09 14:22
 **/

public class OtherTask {


    private final  Object otherLock;

    private final OneTask oneTask;

    public OtherTask(OneTask oneTask,Object otherLock) {
        this.oneTask = oneTask;

        this.otherLock = otherLock;
    }

    public void other(){
        synchronized (otherLock) {

            oneTask.m();
            System.out.println(Thread.currentThread()+" hold two lock. ");
        }
    }

    public void m() {
        synchronized (otherLock) {
            System.out.println("->");
        }
    }
}
