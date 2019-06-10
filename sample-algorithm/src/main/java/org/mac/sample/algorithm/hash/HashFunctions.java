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

package org.mac.sample.algorithm.hash;

/**
 * hash函数
 *
 * @date 2018-12-27
 */
public abstract class HashFunctions {
    private HashFunctions (){}

    /**
     * FNV1_32_HASH算法
     *
     * @param s
     * @return
     */
    public static int fnv1_32_hash(String s){

        final int p = 16777619;
        int hash = (int)2166136261L;

        for (int i = 0; i < s.length(); i++)
            hash = (hash ^ s.charAt(i)) * p;

        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        if (hash < 0)
            hash = Math.abs(hash);

        return hash;
    }
}
