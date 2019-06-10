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

package org.mac.sample.corejava.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Mac
 * @create 2018-06-11 15:43
 **/

public class CompareAndSetLock {

    private final AtomicInteger lock = new AtomicInteger(0);

    private Thread lockHolder;

    public void tryLock() throws TryLockFailException {
        // 只有一个线程能成功获取锁
        boolean success = lock.compareAndSet(0,1);
        if (success) {
            lockHolder = Thread.currentThread();
        }
        else {
            throw new TryLockFailException(Thread.currentThread().getName()+" get lock fail.");
        }
    }

    public void unlock () {
        if (0 == lock.get()) {
            return;
        }
        if (Thread.currentThread() == lockHolder) {
            lock.compareAndSet(1,0);
        }
    }

    class TryLockFailException extends Exception {
        public TryLockFailException (String msg) {
            super(msg);
        }
    }
}
