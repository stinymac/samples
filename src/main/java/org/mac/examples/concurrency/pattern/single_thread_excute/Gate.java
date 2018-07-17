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
package org.mac.examples.concurrency.pattern.single_thread_excute;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-23 20:38
 **/

public class Gate {

    private  String name;
    private  String address;
    private int counter;



    public synchronized boolean pass(final String name, final String address) {
        this.name = name;
        this.address = address;
        this.counter ++;
        return verfiy();
    }

    private boolean verfiy() {
        if (this.name.charAt(0) != this.address.charAt(0)){

            System.out.println("==========broken======"+toString());
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gate{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", counter=" + counter +
                '}';
    }
}
