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

package org.mac.sample.corejava.concurrency.thread.lock.custom;

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
