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
package org.mac.examples.concurrency.pattern.active_object;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-08 15:20
 **/

public class ActiveObjectProxy implements ActiveObject{

    private final Servant servant;
    private final ActivationQueue queue;
    private final Scheduler scheduler;

    public ActiveObjectProxy(Servant servant,  ActivationQueue queue) {
        this.servant = servant;
        this.queue = queue;

        this.scheduler = new Scheduler(queue);
        this.scheduler.start();
    }

    @Override
    public Result<String> buildString(char fillChar, int count) {
        FutureResult futureResult = new FutureResult();
        BuildStringMethod buildStringMethod = new BuildStringMethod(servant,futureResult,fillChar,count);
        queue.put(buildStringMethod);
        return futureResult;
    }

    @Override
    public void displayString(String text) {
        queue.put(new DisplayStringMethod(servant, text));
    }
}
