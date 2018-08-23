 /*
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
  */
package org.mac.examples.concurrency.juc.utils.fork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
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
