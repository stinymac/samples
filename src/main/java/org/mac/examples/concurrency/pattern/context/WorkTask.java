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
package org.mac.examples.concurrency.pattern.context;

/**
 *
 * @author Mac
 * @create 2018-06-06 11:21
 **/

public class WorkTask implements Runnable{
    @Override
    public void run() {
        WorkTaskPart1 part1 = new WorkTaskPart1();
        WorkTaskPart2 part2 = new WorkTaskPart2();

        part1.doPart1();
        part2.doPart2();

        Context context = Context.getContext();

        System.out.println(context.get("id") + "->" + context.get("name"));
    }
}
