/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
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
