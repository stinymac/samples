/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.juc.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Mac
 * @create 2018-06-19 23:55
 **/

public class PerformanceOfConcurrentListTest {
    public static void main(String[] args) {
        Collection c = new ConcurrentLinkedQueue();
        new PerformanceOfConcurrentList(c,5,5000,false,5).test();
        //new PerformanceOfConcurrentList(c,5,500000,true,5).test();

        new PerformanceOfConcurrentList(new CopyOnWriteArrayList<>(),5,5000,false,5).test();
        //new PerformanceOfConcurrentList(new CopyOnWriteArrayList<>(),5,500000,true,5).test();

        new PerformanceOfConcurrentList(Collections.synchronizedList(new ArrayList<>()),5,5000,false,5).test();
        //new PerformanceOfConcurrentList(Collections.synchronizedList(new ArrayList<>()),5,500000,true,5).test();
    }
}
