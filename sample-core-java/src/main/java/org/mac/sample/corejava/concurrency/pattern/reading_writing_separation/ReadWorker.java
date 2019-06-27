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

package org.mac.sample.corejava.concurrency.pattern.reading_writing_separation;

/**
 *
 * @author Mac
 * @create 2018-05-24 22:12
 **/

public class ReadWorker extends Thread{
    private final ReadWriteTask readWriteTask;

    public ReadWorker(ReadWriteTask readWriteTask) {
        this.readWriteTask = readWriteTask;
    }

    @Override
    public void run() {
            while (true) {
                readWriteTask.read();
            }
    }
}
