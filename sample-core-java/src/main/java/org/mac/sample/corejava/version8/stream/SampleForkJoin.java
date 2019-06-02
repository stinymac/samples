/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

package org.mac.sample.corejava.version8.stream;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ForkJoinPool
 * RecursiveTask
 * RecursiveAction
 *
 * @auther mac
 * @date 2019-06-02
 */

public class SampleForkJoin {
    private static final int[] array = new int[]{0,1,2,3,4,5,6,7,8,9};

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0,array.length,array);
        Integer result = forkJoinPool.invoke(task);
        Optional.ofNullable(result).ifPresent(System.out::println);

        AccumulatorRecursiveAction accumulatorRecursiveAction = new AccumulatorRecursiveAction(0,array.length,array);
        forkJoinPool.invoke(accumulatorRecursiveAction);
        System.out.println(AccumulatorRecursiveAction.ResultHelper.getResult());
        AccumulatorRecursiveAction.ResultHelper.rest();

    }

    private static class AccumulatorRecursiveTask extends RecursiveTask<Integer> {

        private final int start;
        private final int end;
        private final int data[];

        private final int LIMIT = 3;

        public AccumulatorRecursiveTask(int start, int end, int[] data) {
            this.start = start;
            this.end = end;
            this.data = data;
        }

        @Override
        protected Integer compute() {

            if (end - start < LIMIT) {
                int result = 0;
                for (int i = start; i < end; i++) {
                    result += data[i];
                }
                return result;
            }
            int middle = (start + end) / 2;

            AccumulatorRecursiveTask left = new AccumulatorRecursiveTask(start,middle,data);
            AccumulatorRecursiveTask right = new AccumulatorRecursiveTask(middle,end,data);

            left.fork();
            Integer rightResult = right.compute();
            Integer leftResult = left.join();

            return leftResult + rightResult;
        }
    }

    private static class AccumulatorRecursiveAction extends RecursiveAction {

        private final int start;
        private final int end;
        private final int data[];

        private final int LIMIT = 3;

        public AccumulatorRecursiveAction(int start, int end, int[] data) {
            this.start = start;
            this.end = end;
            this.data = data;
        }

        @Override
        protected void compute() {
            if (end - start < LIMIT) {

                for (int i = start; i < end; i++) {
                    ResultHelper.accumulate(data[i]);
                }
            }
            else {
                int middle = (start + end) / 2;

                AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start,middle,data);
                AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(middle,end,data);

                left.fork();
                right.fork();
                left.join();
                right.join();
            }
        }

        static class ResultHelper {

            private static final AtomicInteger result = new AtomicInteger();

            static void accumulate(int value) {

                result.getAndAdd(value);
            }

            static int getResult() {
                return result.get();
            }

            static void rest() {
                result.set(0);
            }
        }
    }
}
