/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.observer;

/**
 *
 * @author Mac
 * @create 2018-05-23 16:41
 **/

public class LifeCycleEvent {
    private Status status;
    private Thread thread;
    private Throwable cause;

    public LifeCycleEvent(Status status, Thread thread, Throwable cause) {
        this.status = status;
        this.thread = thread;
        this.cause = cause;
    }

    public Status getStatus() {
        return status;
    }

    public Thread getThread() {
        return thread;
    }

    public Throwable getCause() {
        return cause;
    }

}
