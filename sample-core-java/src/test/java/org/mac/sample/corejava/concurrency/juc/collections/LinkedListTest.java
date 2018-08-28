/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.juc.collections;

/**
 *
 * @author Mac
 * @create 2018-06-17 22:21
 **/

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");
        linkedList.add("E");
        linkedList.addFirst("F");
        linkedList.remove("D");
        linkedList.removeLast();
        System.out.println(linkedList.size());
        System.out.println(linkedList);

        PriorityLinkedList<Integer> list = new PriorityLinkedList<Integer>();
        list.add(1);
        list.add(-10);
        list.add(0);
        list.add(100);
        list.add(25);
        list.add(25);
        list.add(-100);
        System.out.println(list);
    }
}
