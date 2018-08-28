/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mac
 * @create 2018-05-23 15:58
 **/

public class Subject {

    private int status;

    List<Observer> observers = new ArrayList<>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if (status == this.status){
            return;
        }
        this.status = status;
        notifyObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer observer:observers) {
            observer.update(this);
        }
    }
}
