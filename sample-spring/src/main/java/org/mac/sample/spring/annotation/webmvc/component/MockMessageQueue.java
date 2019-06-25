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

package org.mac.sample.spring.annotation.webmvc.component;

import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Mock message queue
 *
 * @auther mac
 * @date 2018-11-08
 */
@Component
public class MockMessageQueue<T> {

    private Queue<T> messageQueue = new ConcurrentLinkedQueue<>();

    public boolean put(T message){
        return messageQueue.add(message);
    }

    public T take(){
        return messageQueue.poll();
    }
}
