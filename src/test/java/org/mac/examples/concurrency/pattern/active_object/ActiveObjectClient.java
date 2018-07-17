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
 * @create 2018-06-08 15:43
 **/

public class ActiveObjectClient {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        Thread buildStringThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0;true;i++){
                        Result result = activeObject.buildString( 'A',i + 1);
                        Thread.sleep(20);
                        String value = (String) result.getResultValue();
                        System.out.println(Thread.currentThread().getName()+": value="+value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread displayStringThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; true; i++) {
                        String text = Thread.currentThread().getName() + ":" + i;
                        activeObject.displayString(text);
                        Thread.sleep(200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        buildStringThread.start();
        displayStringThread.start();
    }
}
