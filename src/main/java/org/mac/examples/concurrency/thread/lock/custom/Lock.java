/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.thread.lock.custom;

/**
 *
 * @author Mac
 * @create 2018-05-09 21:09
 **/
public interface Lock {
    public class TimeoutException extends Exception {
        public TimeoutException () {
            super();
        }

        public TimeoutException(String message) {
            super(message);
        }
    }

    public class IllegalOperationException extends Exception {
        public IllegalOperationException () {
            super();
        }
        public IllegalOperationException (String message) {
            super(message);
        }
    }

    public void lock() throws InterruptedException;

    public void lock(long millis) throws TimeoutException, InterruptedException;

    public void unlock() throws IllegalOperationException;


}
