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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-17 15:28
 **/

public class ForkJoinRecursiveTaskTest {
    private static final int MAX_THRESHOLD = 5;
    static class AccumulateRecursiveTask extends RecursiveTask<Long>{
        private final long start;
        private final long end;

        public AccumulateRecursiveTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_THRESHOLD) {
                long sum = 0;
                for (long i = start; i <= end;i++ ) {
                    sum+=i;
                }
                return sum;
            } else {
                long middle = (end+start)/2;
                AccumulateRecursiveTask leftPartTask = new AccumulateRecursiveTask(start,middle);
                AccumulateRecursiveTask rightPartTask = new AccumulateRecursiveTask(middle+1,end);

                leftPartTask.fork();
                rightPartTask.fork();

                return leftPartTask.join()+rightPartTask.join();
            }
        }
    }

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> future = forkJoinPool.submit(new AccumulateRecursiveTask(0,100000));
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
