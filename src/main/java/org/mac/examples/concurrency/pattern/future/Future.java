package org.mac.examples.concurrency.pattern.future;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-25 20:40
 **/
public interface Future<T> {
    T get () throws InterruptedException;
}
