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

package org.mac.sample.corejava.concurrency.thread.threadpool;

/**
 *
 * @author Mac
 * @create 2018-05-10 16:43
 **/
public interface ThreadPool {

    void submit(Runnable task);

    int getPoolThreadNum();


    int getWaitingTaskNum();

    void shutdown() throws InterruptedException;

    interface RejectionPolicy {
        void reject()  throws RejectException;
    }

    class RejectException extends RuntimeException{
        public RejectException (String message) {
            super(message);
        }
    }
}
