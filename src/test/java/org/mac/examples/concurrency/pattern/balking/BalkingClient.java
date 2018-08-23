/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.balking;

/**
 *
 * @author Mac
 * @create 2018-06-06 14:50
 **/

public class BalkingClient {
    public static void main(String[] args) {
        BalkingData data = new BalkingData("E:\\IdeaProjects\\some-examples\\src\\test\\java\\org\\mac\\examples\\concurrency\\pattern\\balking\\BalkingData.txt","===BEGIN===",true);
        new CustomerThread(data).start();
        new LookOverThread(data).start();
    }
}
