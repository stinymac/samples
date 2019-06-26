/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

package org.mac.sample.corejava.concurrency.pattern.observer;

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
