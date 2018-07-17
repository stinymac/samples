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
package org.mac.examples.concurrency.pattern.two_phase_termination;

import java.io.IOException;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-07 16:08
 **/

public class TwoPhaseTerminationClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.start();
        Thread.sleep(10_000L);

        simpleServer.shutdown();
    }
}
