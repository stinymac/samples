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
package org.mac.examples.concurrency.juc.utils.count_down_latch;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-14 12:38
 **/

public class ClassicUsageTest {
    public static void main(String[] args) {
        ClassicUsage usage = new ClassicUsage();
        usage.case1();
        usage.case2();
    }
}
