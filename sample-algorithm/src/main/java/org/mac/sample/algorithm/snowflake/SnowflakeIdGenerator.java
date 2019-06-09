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

package org.mac.sample.algorithm.snowflake;

import java.util.stream.IntStream;

/**
 * Snowflake algorithm
 *
 * @date 2019-06-09
 */
public class SnowflakeIdGenerator {
    /**starting reference time*/
    private long twepoch = 1538548836300L;

    /**indicate data center*/
    private long dataCenterId;
    /**indicate machine id*/
    private long workerId;
    /**sequence number*/
    private long sequence;

    /**number of bits indicate data center */
    private long dataCenterIdBits = 5L;
    /**number of bits indicate machine */
    private long workerIdBits = 5L;
    /**number of bits indicate sequence */
    private long sequenceBits = 12L;
    /**
     * 利用位运算计算出5位能表示的最大正整数
     *
     * 左移
     *         11111111 11111111 11111111 11111111 //-1的二进制表示（补码）
     *   11111 11111111 11111111 11111111 11100000 //高位溢出的舍弃，低位补0
     *         11111111 11111111 11111111 11100000 //结果
     * 异或
     *         11111111 11111111 11111111 11111111 //-1的二进制表示（补码）
     *     ^   11111111 11111111 11111111 11100000 //两个操作数的位中，相同则为0，不同则为1
     * -------------------------------------------------------------------------------
     *         00000000 00000000 00000000 00011111 //最终结果31
     */
    private long maxDataCenterId = -1L ^ (-1 << dataCenterIdBits);//31
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);//31

    /**left shift bit to suitable location (13 - 17)*/
    private long workerIdShift = sequenceBits; // 12
    /**(18-22)*/
    private long dataCenterIdShift = sequenceBits + dataCenterIdBits; // 17
    /**(23-63) 41位表示时间*/
    private long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits; // 22

    private long sequenceMask = -1L ^ (-1L << sequenceBits);//4095

    private long lastTimestamp = -1L;

    public SnowflakeIdGenerator(long dataCenterId, long workerId, long sequence) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenter Id can't be greater than %d or less than 0",maxDataCenterId));
        }
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
        this.sequence = sequence;
    }

    public long getWorkerId(){
        return workerId;
    }

    public long getDataCenterId(){
        return dataCenterId;
    }

    public long getTimestamp(){
        return System.currentTimeMillis();
    }

    /**
     * id generate algorithm
     * @return snowflake id
     */
    public synchronized long next() {

        long timestamp = System.currentTimeMillis();
        // 系统时钟回拨抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("System clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            // make sure sequence in [0,4095]
            // 通过位与运算保证计算的结果范围始终是 0-4095
            sequence = (sequence + 1) & sequenceMask;
            // sequence overflow in this millisecond wait for next millisecond
            System.out.println("in same millisecond:["+timestamp+"] sequence->["+sequence+"]");
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
                System.out.println("in same millisecond:["+timestamp+"] sequence over wait for next millisecond");
            }
        }
        else {
            sequence = 0;
            System.out.println("in this millisecond:["+timestamp+"] first sequence->["+sequence+"]");
        }

        lastTimestamp = timestamp;
        /*
         * eg.
         *    1|                    41                        |  5  |   5  |     12
         *    0|0001100 10100010 10111110 10001001 01011100 00|00000|0 0000|0000 00000000
         *    0|000000‭0 00000000 00000000 00000000 00000000 00|10001|0 0000|0000 00000000
         *    0|0000000 00000000 00000000 00000000 00000000 00|00000|1 1001|0000 00000000
         * or 0|0000000 00000000 00000000 00000000 00000000 00|00000|0 0000|‭0000 00000000‬
         * -------------------------------------------------------------------------------
         *    0|0001100 10100010 10111110 10001001 01011100 00|10001|1 1001|‭0000 00000000‬
         */

        return ((timestamp - twepoch) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    /**
     * wait next millis
     * @param lastTimestamp
     * @return next millis
     */
    private long tilNextMillis(long lastTimestamp) {

        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        final SnowflakeIdGenerator generator = new SnowflakeIdGenerator(0,0,0);
        IntStream.rangeClosed(1,10).forEach(i -> {
            String id = generator.next()+"";
            System.out.println("id:"+id+"\n");
        });
    }
}
