/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mac.examples.concurrency.thread.threadpool;

import org.mac.examples.utils.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *Simple Thread Pool
 *
 * @author Mac
 * @create 2018-05-10 16:53
 **/

public class SimpleThreadPool extends Thread implements ThreadPool {

    private static final int DEFAULT_POOL_INIT_THREAD_NUM = 10;

    private static final int DEFAULT_POOL_ACTIVE_THREAD_NUM = 20;

    private static final int DEFAULT_POOL_MAX_THREAD_NUM = 50;

    private static final int DEFAULT_TASK_QUEUE_SIZE = 100;

    private static final String THREAD_NAME_PREFIX = "Simple_Thread_Pool-";

    private  int initThreadNum;
    private  int activeThreadNum;
    private  int maxThreadNum;
    private  int taskQueueSize;

    private List<Executor> pool;
    private LinkedList<Runnable> taskQueue;

    public  RejectionPolicy rejectionPolicy;
    public static RejectionPolicy DEFAULT_REJECTION_POLICY = new RejectionPolicy(){
        public void reject()  throws RejectException {
            throw new RejectException("Task pool full,The new task will be discarded.");
        }
    };

    private  volatile AtomicInteger threadNumSeq = new AtomicInteger();

    private volatile boolean isDestroy = false;

    public SimpleThreadPool() {
        loadConfiguration();
        init();
    }

    private  void loadConfiguration () {
        try {
            Properties properties = new Properties();
            properties.load(SimpleThreadPool.class.getResourceAsStream("init.properties"));

            String poolInitSize = properties.getProperty("pool-init-size");
            initThreadNum = (StringUtils.isNotPositiveInteger(poolInitSize))?DEFAULT_POOL_INIT_THREAD_NUM:Integer.parseInt(poolInitSize);

            String poolActiveSize = properties.getProperty("pool-active-size");
            activeThreadNum = (StringUtils.isNotPositiveInteger(poolActiveSize )) ? DEFAULT_POOL_ACTIVE_THREAD_NUM : Integer.parseInt(poolActiveSize);

            String poolMaxSize = properties.getProperty("pool-max-size");
            maxThreadNum = (StringUtils.isNotPositiveInteger(poolMaxSize)) ? DEFAULT_POOL_MAX_THREAD_NUM :Integer.parseInt(poolMaxSize);

            String taskWaitingQueueSize = properties.getProperty("task-waiting-queue-size");
            taskQueueSize = (StringUtils.isNotPositiveInteger(taskWaitingQueueSize)) ? DEFAULT_TASK_QUEUE_SIZE :Integer.parseInt(taskWaitingQueueSize);
        } catch (Exception e) {
            applyDefaultConfiguration();
        }
        if (!(maxThreadNum >= activeThreadNum && activeThreadNum >= initThreadNum)) {
            applyDefaultConfiguration();
        }
    }

    private void applyDefaultConfiguration(){
        initThreadNum = DEFAULT_POOL_INIT_THREAD_NUM;
        activeThreadNum = DEFAULT_POOL_ACTIVE_THREAD_NUM;
        maxThreadNum = DEFAULT_POOL_MAX_THREAD_NUM;
        taskQueueSize =DEFAULT_TASK_QUEUE_SIZE;
        rejectionPolicy = DEFAULT_REJECTION_POLICY;
    }

    private void init() {
        this.taskQueue = new LinkedList<Runnable>();
        this.pool = new ArrayList<Executor>(maxThreadNum);
        for (int i = 0; i < initThreadNum; i++) {
            newExecutor();
        }
        this.start();
    }

    private void newExecutor() {
        Executor executor = new Executor(THREAD_NAME_PREFIX+(threadNumSeq.addAndGet(1)));
        pool.add(executor);
        executor.start();
    }

    public void submit(Runnable task) {
        if(task == null){
            return;
        }
        if (isDestroy) {
            throw new IllegalStateException("thread pool destroyed.");
        }
        synchronized (taskQueue) {
            if (taskQueue.size() > taskQueueSize) {
                rejectionPolicy.reject();
                return;
            }
            taskQueue.addLast(task);
            taskQueue.notifyAll();
        }
    }

    public void shutdown() throws InterruptedException {
        this.isDestroy = true;
        while (!taskQueue.isEmpty()) {
            Thread.sleep(50);
        }
        synchronized (pool) {
            int executorNum = pool.size();
            while (executorNum > 0) {
                for (Executor executor : pool) {
                    if (executor.status() == Status.BLOCKED) {
                        executor.turnOff();
                        executor.interrupt();
                    } else if (executor.status() == Status.FREE) {
                        executor.turnOff();
                    } else if (executor.status() == Status.DEAD) {
                        executorNum--;
                    } else {
                        Thread.sleep(50);
                    }
                }
            }
            pool.clear();
        }
    }

    @Override
    public void run() {
        selfMaintenance ();
    }

    private void selfMaintenance () {
        while (!this.isDestroy) {
            System.out.printf("Thread pool initSize:%d,activeSize:%d,maxSize:%d," +
                            "taskQueueSize:%d,current thread number in pool:%d," +
                            "current task number in task queue:%d\n",initThreadNum,activeThreadNum,maxThreadNum,taskQueueSize,
                    pool.size(),taskQueue.size());
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            int currentThreadNum = pool.size();
            int currentTaskNum =  taskQueue.size();

            if (currentTaskNum  > currentThreadNum && currentThreadNum < activeThreadNum) {
                int num = activeThreadNum - currentThreadNum;
                increase (num);
            }
            if (currentTaskNum  > currentThreadNum && currentThreadNum < maxThreadNum) {
                int num = maxThreadNum - currentThreadNum;
                increase (num);
            }

            synchronized (pool) {
                int currentPoolSize = pool.size();
                if (taskQueue.isEmpty() && currentPoolSize > activeThreadNum) {
                    int releaseNum = currentPoolSize - activeThreadNum;
                    for (Iterator<Executor> it = pool.iterator();it.hasNext();){
                        if (releaseNum <= 0) {
                            break;
                        }
                        Executor t = it.next();
                        if (t.status != Status.RUNNING) {
                            t.turnOff();
                            t.interrupt();
                            it.remove();
                            releaseNum--;
                        }
                    }
                }
            }
        }
    }

    private void increase (int num) {
        for (int i = 0; i < num; i++) {
            newExecutor();
        }
    }

    public int getPoolThreadNum(){ return pool.size();}

    public int getWaitingTaskNum() {
        return taskQueue.size();
    }

    private  class Executor extends Thread {

        private volatile Status status = Status.FREE;

        public Executor(String name) {
            super(name);
        }

        public Status status() {
            return this.status;
        }

        public void turnOff() {
            this.status = Status.DEAD;
        }

        @Override
        public void run() {
            OUTER:
            while (this.status != Status.DEAD) {
                final Runnable task;
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait();
                            this.status = Status.BLOCKED;
                        } catch (InterruptedException e) {
                            //e.printStackTrace();
                            break OUTER;
                        }
                    }
                    task = taskQueue.removeFirst();
                }
                if (task != null) {
                    this.status = Status.RUNNING;
                    task.run();
                    this.status = Status.FREE;
                }
            }
        }
    }

    enum Status {
        FREE,RUNNING,BLOCKED,DEAD
    }
}