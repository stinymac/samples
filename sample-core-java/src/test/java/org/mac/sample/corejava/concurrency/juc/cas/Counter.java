/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.juc.cas;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Mac
 * @create 2018-06-12 10:20
 **/
public interface Counter {

    public void increment() ;

    public long getCountResult();

    static class IncorrectCounter implements Counter {

        private long counter;


        public void increment() {
            counter++;
        }


        public long getCountResult() {
            return counter;
        }
    }

    static class CounterUseSynchronized implements Counter {

        private long counter;


        public synchronized void increment() {
            counter++;
        }


        public long getCountResult() {
            return counter;
        }
    }

    static class CounterUseAtomicLong implements Counter {

        private AtomicLong counter = new AtomicLong(0);

        public  void increment() {
            counter.incrementAndGet();
        }

        public long getCountResult() {
            return counter.get();
        }
    }

    static class CounterUseLock implements Counter {

        private long counter ;
        private final Lock lock = new ReentrantLock();

        public  void increment() {
            try {
                lock.lock();
                counter++;
            }finally {
                lock.unlock();
            }
        }


        public long getCountResult() {
            return counter;
        }
    }


    static class CounterUseUnsafe implements Counter {

        private volatile long counter = 0;
        private Unsafe unsafe ;
        private long offset;

        public CounterUseUnsafe() {
            this.counter = counter;
            this.unsafe = ReflectUnsafe.getUnsafeInstance();
            try {
                this.offset = unsafe.objectFieldOffset(CounterUseUnsafe.class.getDeclaredField("counter"));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        public  void increment() {
            unsafe.getAndAddLong(this, offset, 1L);
        }

        public long getCountResult() {
            return counter;
        }
    }
}
