/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.juc.collections;

import java.util.Random;

/**
 *
 * @author Mac
 * @create 2018-06-17 23:19
 **/

public class SkipList<E extends Comparable<E>> {

    private static final int NODE_TYPE_HEAD = -1;
    private static final int NODE_TYPE_TAIL = 1;
    private static final int NODE_TYPE_ELEMENT = 0;

    private static final double INDEX_PROBABILITY = 0.5;
    private static final int MAX_INDEX_LEVEL = 10;

    private final class Node<E extends Comparable<E>> {

        private E element;

        private Node<E> up;
        private Node<E> down;
        private Node<E> next;
        private Node<E> prev;

        private int type;

        public Node(E element, int type) {
            this.element = element;
            this.type = type;
        }
    }

    private Node<E> head;
    private Node<E> tail;

    private int size;
    private int elementCount;
    private int leveleSize;

    private  final Random random;

    public SkipList () {

        this.head = new Node<E>(null,NODE_TYPE_HEAD);
        this.tail = new Node<E>(null,NODE_TYPE_TAIL);

        this.head.next = this.tail;
        this.tail.prev = this.head;

        random  = new Random(System.currentTimeMillis());
    }

    public boolean contains(E e) {
        if (e == null) {
            throw new IllegalArgumentException("element can not null");
        }
        return e.equals(findAndPositioning ( e));
    }

    public int size() {
        return this.size;
    }

    public boolean add (E e) {
        if (e == null) {
            throw new IllegalArgumentException("element can not null");
        }
        //TODO
        return false;

    }

    public boolean remove (E e) {
        if (e == null) {
            throw new IllegalArgumentException("element can not null");
        }
        //TODO
        return false;
    }

    /**
     * 查找或插入时定位位置
     * 存在给定元素返回该元素位置
     * 不存在则返回其应处位置的前一个元素的位置
     * @param e
     * */

    private Node<E> findAndPositioning (E e) {
        //TODO
        return null;
    }

    private boolean isCreateIndex (int currentLevel) {
        return random.nextDouble() < INDEX_PROBABILITY && currentLevel < MAX_INDEX_LEVEL;
    }
}
