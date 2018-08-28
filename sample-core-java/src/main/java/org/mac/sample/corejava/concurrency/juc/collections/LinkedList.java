 /*
  *      (             |"|           !!!       #   ___                             o
  *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
  *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  *
  * 虽不能至,心向往之。(Although I can't, my heart is longing for it.)
  *
  *
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  */
package org.mac.sample.corejava.concurrency.juc.collections;

/**
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
