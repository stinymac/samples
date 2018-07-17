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
package org.mac.examples.concurrency.pattern.balking;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-06 14:50
 **/

public class BalkingClient {
    public static void main(String[] args) {
        BalkingData data = new BalkingData("E:\\IdeaProjects\\some-examples\\src\\test\\java\\org\\mac\\examples\\concurrency\\pattern\\balking\\BalkingData.txt","===BEGIN===",true);
        new CustomerThread(data).start();
        new LookOverThread(data).start();
    }
}
