 /*
  *      (             |"|           !!!       #   ___                             o
  *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
  *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  *
  * 虽不能至,心向往之。(Although I can't, my heart is longing for it.)
  *
  *
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  */
package org.mac.sample.corejava.concurrency.thread.shutdown.forcible;

/**
 * 将工作线程设置为守护线程
 * 创建工作线程的线程结束后
 * 守护线程立即退出
 *
 * @author Mac
 * @create 2018-05-08 22:17
 **/

public class ThreadService {

    private volatile boolean finished = false;

    private Thread executeThread;

    public void execute(final Runnable task) {
        executeThread = new Thread(new Runnable() {
            public void run() {
                Thread worker = new Thread(task);
                worker.setDaemon(true);
                worker.start();

                try {//等待worker结束
                    worker.join();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
                finished = true;
            }
        });
        executeThread.start();
    }

    public void shutdown(long millis) {
        long beginTimeMillis = System.currentTimeMillis();
        while (!finished) {
            if (System.currentTimeMillis() - beginTimeMillis >= millis){
                executeThread.interrupt();
                finished = true;
            }
        }
    }
}
