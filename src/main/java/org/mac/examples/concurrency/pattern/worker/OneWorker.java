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

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-07 17:43
 **/

public class OneWorker extends Thread{
    private ProductionLine productionLine;

    public OneWorker(String name,ProductionLine productionLine) {
        super("name");
        this.productionLine = productionLine;
    }

    @Override
    public void run() {
        while(true) {
            ProductionLine.Product p = this.productionLine.StepOne();
            System.out.println("Step one->" + p);
        }
    }
}
