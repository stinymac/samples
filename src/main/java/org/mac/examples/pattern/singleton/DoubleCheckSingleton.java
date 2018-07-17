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
package org.mac.examples.pattern.singleton;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-21 15:11
 **/

public class DoubleCheckSingleton {
    // 使用volatile 阻止重排序优化而造成的返回的实例对象使用时的NullPointException
    private static  volatile DoubleCheckSingleton INSTANCE ;

    private DoubleCheckSingleton(){

    }

    public  static DoubleCheckSingleton getInstance(){
        if (null == INSTANCE) {

            synchronized (DoubleCheckSingleton.class) {

                if (null == INSTANCE) {
                    INSTANCE = new DoubleCheckSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
