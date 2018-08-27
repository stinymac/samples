/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.worker;

/**
 *
 * @author Mac
 * @create 2018-06-07 18:26
 **/

public class WorkerClient {
    public static void main(String[] args) {
        ProductionLine assemblyLine = new ProductionLine(5,5);
        assemblyLine.startWork();
    }
}
