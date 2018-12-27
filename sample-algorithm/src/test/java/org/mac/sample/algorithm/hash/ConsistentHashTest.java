/*
 *      (             |"|           !!!       #   ___                             o
 *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
 *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 *
 *
 *                    !!!         |                             |"|            _             o          _     _
 *    __MMM__      `  _ _  '      |.===.         ,,,,,         _|_|_         _|_|_        ` /_\ '     o' \,=./ `o
 *     (o o)      -  (OXO)  -     {}o o{}       /(o o)\        (o o)         (o o)       - (o o) -       (o o)
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 *
 * 虽不能至,心向往之。(Although it is not possible, my heart is longing for it.)
 *
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 */

package org.mac.sample.algorithm.hash;

import org.junit.Test;

import java.util.Random;

/**
 * Test ConsistentHash
 *
 * @auther mac
 * @date 2018-12-27
 */
public class ConsistentHashTest {

    public String nextKey(int i) {
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int index = new Random().nextInt(letters.length());
        int end = ((index+3) > letters.length()-1)?index:(index+3);
        return letters.substring(index,end)+i;
    }

    /**
     * 一次运行结果统计
     *
     * 192.168.36.100	2940
     * 192.168.36.101	2965
     * 192.168.36.102	3781
     * 192.168.36.103	314
     */
    @Test
    public void testPlainConsistentHash(){
        int size = 10000;

        for (int i = 0; i < size; i++) {
            String key = nextKey(i);
            System.out.println( "keys["+i+"]:"+key+" hash:"+HashFunctions.fnv132Hash(key)+" route to server:"+ PlainConsistentHash.routeToServer(key));
        }
    }

    /**
     * 两次运行结果统计
     *********************************
     * 192.168.36.100:6379	2792
     * 192.168.36.101:6379	2410
     * 192.168.36.102:6379	2324
     * 192.168.36.103:6379	2474
     *********************************
     * 192.168.36.100:6379	2774
     * 192.168.36.101:6379	2366
     * 192.168.36.102:6379	2430
     * 192.168.36.103:6379	2430
     *
     */
    @Test
    public void testImprovedConsistentHash(){
        int size = 10000;

        for (int i = 0; i < size; i++) {
            String key = nextKey(i);
            System.out.println( key+"->"+ImprovedConsistentHash.routeToServer(key) );
        }
    }
}
