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

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-19 22:04
 **/

public class ConcurrentSkipListMapTest {

    public static void main(String[] args) {

        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();

        concurrentSkipListMap.put(1,"A");
        concurrentSkipListMap.put(4,"B");
        concurrentSkipListMap.put(5,"C");
        concurrentSkipListMap.put(6,"D");

        concurrentSkipListMap.merge(1,"Z",(ov,v)->{
            return ov + "-" + v;
        });

        System.out.println(concurrentSkipListMap.get(1));

        concurrentSkipListMap.compute(1,(k,v)->{
            System.out.println(k + ":"+v);
            return "";
        });

    }
}
