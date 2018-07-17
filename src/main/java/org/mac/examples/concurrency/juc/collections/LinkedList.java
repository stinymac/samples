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
 * @create 2018-06-17 21:28
 **/

public class LinkedList<E> {

    private  final class Node<E> {
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


    public LinkedList() {
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

        if( this.tail == null) {
            head = tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }

        this.size++;
    }

    public void remove(E e){
        Node<E> h = this.head;

        while (h != null && ! h.element.equals(e)) {
            h = h.next;
        }

        if (h != null) {
            h.prev.next = h.next;
            h.next.prev = h.prev;
            this.size--;
        }
    }

    public void addFirst(E e){

        Node<E> newNode = new Node<E>(e);

        if (head == null) {
            head = tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        this.size++;
    }

    public Node<E> removeLast(){
        if (tail == null) {
            return null;
        }
        tail.prev.next = null;
        tail = tail.prev;

        this.size--;

        return tail;
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
