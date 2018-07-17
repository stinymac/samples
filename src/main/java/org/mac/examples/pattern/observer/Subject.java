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
package org.mac.examples.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry class description
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
