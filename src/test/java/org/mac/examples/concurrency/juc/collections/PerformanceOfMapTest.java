/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.juc.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author Mac
 * @create 2018-06-19 14:22
 **/

public class PerformanceOfMapTest {
    /**
     *
     ---write
     Test Map [java.util.Hashtable] with [5] Thread and data size is [5000000] and do test [5] times
     insert or get 25000000 data  take [11389] ms
     insert or get 25000000 data  take [9173] ms
     insert or get 25000000 data  take [9110] ms
     insert or get 25000000 data  take [9012] ms
     insert or get 25000000 data  take [8956] ms
     total time is [47640] ms and average take [9528]  ms
     ---write and read
     Test Map [java.util.Hashtable] with [5] Thread and data size is [5000000] and do test [5] times
     insert or get 25000000 data  take [14055] ms
     insert or get 25000000 data  take [13471] ms
     insert or get 25000000 data  take [13361] ms
     insert or get 25000000 data  take [13479] ms
     insert or get 25000000 data  take [13169] ms
     total time is [67535] ms and average take [13507] ms
     ==================================================================
     ---write
     Test Map [java.util.Collections$SynchronizedMap] with [5] Thread and data size is [5000000] and do test [5] times
     insert or get 25000000 data  take [10351] ms
     insert or get 25000000 data  take [9798] ms
     insert or get 25000000 data  take [9760] ms
     insert or get 25000000 data  take [10458] ms
     insert or get 25000000 data  take [9756] ms
     total time is [50123] ms and average take [10024] ms
     ---write and read
     Test Map [java.util.Collections$SynchronizedMap] with [5] Thread and data size is [5000000] and do test [5] times
     insert or get 25000000 data  take [14078] ms
     insert or get 25000000 data  take [13988] ms
     insert or get 25000000 data  take [13710] ms
     insert or get 25000000 data  take [13989] ms
     insert or get 25000000 data  take [13696] ms
     total time is [69461] ms and average take [13892] ms
     ==================================================================
     ---write
     Test Map [java.util.concurrent.ConcurrentHashMap] with [5] Thread and data size is [5000000] and do test [5] times
     insert or get 25000000 data  take [4687] ms
     insert or get 25000000 data  take [3482] ms
     insert or get 25000000 data  take [3477] ms
     insert or get 25000000 data  take [3447] ms
     insert or get 25000000 data  take [3401] ms
     total time is [18494] ms and average take [3698] ms
     ---write and read
     Test Map [java.util.concurrent.ConcurrentHashMap] with [5] Thread and data size is [5000000] and do test [5] times
     insert or get 25000000 data  take [4010] ms
     insert or get 25000000 data  take [3852] ms
     insert or get 25000000 data  take [3785] ms
     insert or get 25000000 data  take [3636] ms
     insert or get 25000000 data  take [3554] ms
     total time is [18837] ms and average take [3767] ms
     ==================================================================
     * */
    public static void main(String[] args) {
        //new PerformanceOfMap(new Hashtable(),5,500000,5,false).test();
        //new PerformanceOfMap(new Hashtable(),5,500000,5,true).test();

        //new PerformanceOfMap(Collections.synchronizedMap(new HashMap()),5,500000,5,false).test();
        //new PerformanceOfMap(Collections.synchronizedMap(new HashMap()),5,500000,5,true).test();

        new PerformanceOfMap(new ConcurrentHashMap(),5,500000,5,false).test();
        new PerformanceOfMap(new ConcurrentHashMap(),5,500000,5,true).test();

        new PerformanceOfMap(new ConcurrentSkipListMap<>(),5,500000,5,false).test();
        new PerformanceOfMap(new ConcurrentSkipListMap<>(),5,500000,5,true).test();

        new PerformanceOfMap(new ConcurrentHashMap(),10,500000,5,false).test();
        new PerformanceOfMap(new ConcurrentHashMap(),10,500000,5,true).test();

        new PerformanceOfMap(new ConcurrentSkipListMap<>(),10,500000,5,false).test();
        new PerformanceOfMap(new ConcurrentSkipListMap<>(),10,500000,5,true).test();

        new PerformanceOfMap(new ConcurrentHashMap(),20,500000,5,false).test();
        new PerformanceOfMap(new ConcurrentHashMap(),20,500000,5,true).test();

        new PerformanceOfMap(new ConcurrentSkipListMap<>(),20,500000,5,false).test();
        new PerformanceOfMap(new ConcurrentSkipListMap<>(),20,500000,5,true).test();


        new PerformanceOfMap(new ConcurrentHashMap(),100,500000,5,false).test();
        new PerformanceOfMap(new ConcurrentHashMap(),100,500000,5,true).test();

        new PerformanceOfMap(new ConcurrentSkipListMap<>(),100,500000,5,false).test();
        new PerformanceOfMap(new ConcurrentSkipListMap<>(),100,500000,5,true).test();
    }
}
