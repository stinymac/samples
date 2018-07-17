package org.mac.examples.concurrency.thread.lock.custom;

/**
 * Entry class description
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
