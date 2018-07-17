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
 * @create 2018-06-08 10:00
 **/

public class Servant implements ActiveObject{
    @Override
    public Result<String> buildString(char fillChar, int count) {
        char[] buff = new char[count];
        for (int i = 0; i < count; i++) {
            buff[i] = fillChar;
        }
        simulateElapsedTime (10);
        return new RealResult<String>(new String(buff));
    }

    @Override
    public void displayString(String text) {
        simulateElapsedTime (10);
        System.out.println("-> "+text);
    }

    private void simulateElapsedTime (long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
