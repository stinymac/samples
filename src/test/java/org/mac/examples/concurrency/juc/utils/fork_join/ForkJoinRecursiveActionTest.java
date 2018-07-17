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
package org.mac.examples.concurrency.juc.utils.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-17 15:59
 **/

public class ForkJoinRecursiveActionTest {

    private static final int MAX_THRESHOLD = 5;

    private static final AtomicLong SUM = new AtomicLong(0);

    static class AccumulateRecursiveAction extends RecursiveAction {
        private final long start;
        private final long end;

        public AccumulateRecursiveAction(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_THRESHOLD) {
                for (long i = start; i <= end;i++ ) {
                    SUM.addAndGet(i);
                }
            } else {
                long middle = (end+start)/2;
                AccumulateRecursiveAction leftPartTask = new AccumulateRecursiveAction(start,middle);
                AccumulateRecursiveAction rightPartTask = new AccumulateRecursiveAction(middle+1,end);

                leftPartTask.fork();
                rightPartTask.fork();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            final ForkJoinPool forkJoinPool = new ForkJoinPool();
            forkJoinPool.submit(new AccumulateRecursiveAction(0,10));
            // System.out.println(SUM.get());
            forkJoinPool.awaitTermination(3,TimeUnit.SECONDS);
            System.out.println(SUM.get());
        }
    }
}
