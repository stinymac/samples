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
package org.mac.examples.concurrency.pattern.worker;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-07 17:42
 **/

public class ProductionLine {


    private Product[] productCaches;
    private static final int MAX_LIMIT = 100;
    private int counter;
    private int tail;
    private int head;
    private final AtomicInteger seq =  new AtomicInteger();

    private OneWorker[] oneWorkers;
    private OtherWorker[] otherWorkers;

    public ProductionLine(int oneWorkersAmount, int otherWorkersAmount) {
        this.productCaches = new Product[MAX_LIMIT];
        this.tail = 0;
        this.head = 0;
        this.counter = 0;
        this.oneWorkers = new OneWorker[oneWorkersAmount];
        this.otherWorkers = new OtherWorker[otherWorkersAmount];

        init();
    }

    private void init() {
        for (int i = 0;i < oneWorkers.length; i++) {
            oneWorkers[i] = new OneWorker("OneWorker-"+(i+1),this);
        }
        for (int i = 0;i < otherWorkers.length; i++) {
            otherWorkers[i] = new OtherWorker("OtherWorker-"+(i+1),this);
        }
    }


    public synchronized Product StepOne() {
        while (counter > MAX_LIMIT) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Product product = new Product("No"+seq.incrementAndGet(),"P");
        this.productCaches[tail] = product;
        this.tail = (tail + 1) % productCaches.length;
        this.counter++;
        this.notifyAll();
        return product;
    }

    public synchronized Product StepTwo() {
        while (counter <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Product product = this.productCaches[head];
        this.head = (this.head + 1) % this.productCaches.length;
        this.counter--;
        this.notifyAll();
        return product;
    }

    public void startWork() {
        for (int i = 0;i < oneWorkers.length; i++) {
            oneWorkers[i].start();
        }
        for (int i = 0;i < otherWorkers.length; i++) {
            otherWorkers[i].start();
        }
    }
    class Product {
        private final String no;
        private final String name;

        public Product(String no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "no='" + no + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
