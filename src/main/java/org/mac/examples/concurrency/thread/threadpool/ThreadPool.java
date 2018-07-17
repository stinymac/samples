package org.mac.examples.concurrency.thread.threadpool;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-10 16:43
 **/
public interface ThreadPool {

    public void submit(Runnable task);

    public int getPoolThreadNum();


    public int getWaitingTaskNum();

    public void shutdown() throws InterruptedException;

    public interface RejectionPolicy {
        public void reject()  throws RejectException;
    }

    public static class RejectException extends RuntimeException{
        public RejectException (String message) {
            super(message);
        }
    }
}
