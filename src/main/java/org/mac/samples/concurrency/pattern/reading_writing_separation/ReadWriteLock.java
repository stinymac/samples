/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.reading_writing_separation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mac
 * @create 2018-05-24 18:45
 **/

public class ReadWriteLock {

    private volatile int reading = 0;
    private volatile int writing = 0;

    private volatile int readWaiting = 0;
    private volatile int writeWaiting = 0;

    private Thread writeLockHolder;
    private List<Thread> readLockHolders = new ArrayList<Thread>();

    private boolean biasedWrite = true;


    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean biasedWrite) {
        this.biasedWrite = biasedWrite;
    }

    public synchronized void readLock() throws InterruptedException {
        readWaiting++;
        try {
            while (writing > 0 || (biasedWrite && writeWaiting > 0)) {
                this.wait();
            }
            reading++;
            this.readLockHolders.add(Thread.currentThread());

        } finally {
            readWaiting--;
        }
    }

    public synchronized void readUnLock() throws IllegalOperationException {
        if (! this.readLockHolders.contains(Thread.currentThread())) {
            throw new IllegalOperationException("The lock only be released by itself.");
        }
        reading--;
        readLockHolders.remove(Thread.currentThread());
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        writeWaiting++;
        try {
            while (reading > 0 || writing > 0) {
                this.wait();
            }

            writing++;
            this.writeLockHolder = Thread.currentThread();
        } finally {
            writeWaiting--;
        }
    }

    public synchronized void writeUnLock() throws IllegalOperationException {
        if (this.writeLockHolder != Thread.currentThread()) {
            throw new IllegalOperationException("The lock only be released by itself.");
        }
        writeLockHolder = null;
        writing--;
        this.notifyAll();
    }

    public class IllegalOperationException extends Exception {
        public IllegalOperationException () {
            super();
        }
        public IllegalOperationException (String message) {
            super(message);
        }
    }
}
