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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Mac
 * @create 2018-05-09 21:14
 **/

public class SimpleCustomLock implements Lock{

    private volatile boolean lockIsOccupied = false;
    private Collection<Thread> waitSet = new ArrayList<Thread>();
    private Thread lockHolder;

    public synchronized void lock() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        while (lockIsOccupied) {
            waitSet.add(currentThread);
            this.wait();
        }
        lockIsOccupied = true;
        lockHolder = currentThread;
        waitSet.remove(currentThread);
    }

    public synchronized void unlock() throws IllegalOperationException {
        Thread currentThread = Thread.currentThread();
        if(currentThread != lockHolder) {
            throw new IllegalOperationException("The lock only be released by itself.");
        }
        lockIsOccupied = false;
        this.notifyAll();
    }

    public synchronized void lock(long millis) throws TimeoutException, InterruptedException {
        if (millis <=0) {
            lock();
        }

        long beginTimeMillis = System.currentTimeMillis();
        long remainingMillis = millis;
        long endTimeMillis = beginTimeMillis + millis;

        Thread currentThread = Thread.currentThread();

        while (lockIsOccupied) {
            if (remainingMillis <= 0) {
                waitSet.remove(currentThread);
                throw new TimeoutException("get lock time out.");
            }
            waitSet.add(currentThread);
            this.wait(millis);

            remainingMillis = endTimeMillis - System.currentTimeMillis();
            //System.out.println("remainingMillis:"+remainingMillis);
        }
        lockIsOccupied = true;
        lockHolder = currentThread;
        waitSet.remove(currentThread);
    }

    public Collection<Thread> getBlockedThreads() {
        return Collections.unmodifiableCollection(waitSet);
    }

    public int getBlockedThreadNum(){
        return waitSet.size();
    }
}
