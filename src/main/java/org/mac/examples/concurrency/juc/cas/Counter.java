package org.mac.examples.concurrency.juc.cas;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-12 10:20
 **/
public interface Counter {

    public void increment() ;

    public long getCountResult();

    static class IncorrectCounter implements Counter {

        private long counter;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCountResult() {
            return counter;
        }
    }

    static class CounterUseSynchronized implements Counter {

        private long counter;

        @Override
        public synchronized void increment() {
            counter++;
        }

        @Override
        public long getCountResult() {
            return counter;
        }
    }

    static class CounterUseAtomicLong implements Counter {

        private AtomicLong counter = new AtomicLong(0);

        @Override
        public  void increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCountResult() {
            return counter.get();
        }
    }

    static class CounterUseLock implements Counter {

        private long counter ;
        private final Lock lock = new ReentrantLock();
        @Override
        public  void increment() {
            try {
                lock.lock();
                counter++;
            }finally {
                lock.unlock();
            }
        }

        @Override
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

        @Override
        public  void increment() {
            unsafe.getAndAddLong(this, offset, 1L);
        }

        @Override
        public long getCountResult() {
            return counter;
        }
    }
}
