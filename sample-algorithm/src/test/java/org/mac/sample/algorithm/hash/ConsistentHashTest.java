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

import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.junit.Test;
import org.mac.sample.algorithm.hash.rendezvous.RendezvousHash;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
            System.out.println( "keys["+i+"]:"+key+" hash:"+HashFunctions.fnv1_32_hash(key)+" route to server:"+ PlainConsistentHash.routeToServer(key));
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

    /**
     * 一次运行结果统计
     *
     * 192.168.36.100:6379	2438
     * 192.168.36.101:6379	2570
     * 192.168.36.102:6379	2545
     * 192.168.36.103:6379	2447
     *
     * HashFunction :
     *
     * @see com.google.common.hash.SipHashFunction
     * @see com.google.common.hash.Murmur3_32HashFunction
     * @see com.google.common.hash.Murmur3_128HashFunction
     * @see com.google.common.hash.ChecksumHashFunction
     * @see com.google.common.hash.Crc32cHashFunction
     * @see com.google.common.hash.MessageDigestHashFunction
     * ......
     */
    @Test
    public void testRendezvousHash(){
        final HashFunction hashFunc = Hashing.murmur3_128();
        final Funnel<CharSequence> strFunnel = Funnels.stringFunnel(Charset.defaultCharset());

        final List<String> realServerNodes = new LinkedList<String>(Arrays.asList(
                "192.168.36.100:6379",
                "192.168.36.101:6379",
                "192.168.36.102:6379",
                "192.168.36.103:6379")
        );

        RendezvousHash<String, String> rendezvousHash = new RendezvousHash(hashFunc, strFunnel, strFunnel, realServerNodes);

        int size = 10000;

        for (int i = 0; i < size; i++) {
            String key = nextKey(i);
            System.out.println( key+"->"+rendezvousHash.get(key) );
        }
    }
}
