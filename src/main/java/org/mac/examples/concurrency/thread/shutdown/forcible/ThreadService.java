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
package org.mac.examples.concurrency.thread.shutdown.forcible;

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
