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
package org.mac.examples.concurrency.juc.collections;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-17 22:51
 **/

public class PriorityLinkedList<E extends Comparable<E>> {

    private  final class Node<E extends Comparable<E>> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(E element) {
            this.element = element;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;


    public PriorityLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }



    public void add(E e){

        Node<E> newNode = new Node<E>(e);

        if (tail == null) {
            head = tail = newNode;
        } else {
            Node t = tail;
            while (t != null && t.element.compareTo(e) > 0) {
                t = t.prev;
            }
            if (t != null) {
                if (t == tail) {
                    t.next = newNode;
                    newNode.prev = t;
                    tail = newNode;
                } else {
                    newNode.next = t.next;
                    t.next.prev = newNode;
                    t.next = newNode;
                    newNode.prev = t;
                }
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }

        }
        this.size++;
    }


    @Override
    public String toString() {
        Node<E> h = head;

        StringBuilder builder = new StringBuilder("[ ");
        while (h != null ) {
            E e = h.element;
            if(h.next == null) {
                builder.append(e.toString());
            } else {
                builder.append(e.toString()+",");
            }
            h =  h.next;
        }
        builder.append(" ]");
        return builder.toString();
    }

}
