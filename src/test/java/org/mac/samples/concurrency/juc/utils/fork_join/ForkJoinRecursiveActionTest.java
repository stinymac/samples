 /*
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
  */
package org.mac.samples.concurrency.juc.utils.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
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
