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

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 不使用虚拟节点的一致性hash
 *
 * from <a>https://www.cnblogs.com/xrq730/p/5186728.html</a>
 *
 * @auther mac
 * @date 2018-12-26
 */
public class PlainConsistentHash {
    /**
     * 服务器节点
     */
    private static String[] servers = {

            "192.168.36.100:6379",
            "192.168.36.101:6379",
            "192.168.36.102:6379",
            "192.168.36.103:6379",
            "192.168.36.104:6379"
    };

    /**hash环 key表示服务器的hash值，value表示服务器的名称*/
    private static SortedMap<Integer, String> buckets =  new TreeMap<Integer, String>();

    static {
        for (int i = 0 ,size = servers.length; i < size; i++) {
            /*String 本身的hash方法此时产生结果分布在一个很小的区间,即hash环数据倾斜*/
            //System.out.println("servers["+i+"]:"+HashFunctions.fnv1_32_hash(servers[i]));
            buckets.put(HashFunctions.fnv1_32_hash(servers[i]),servers[i]);
        }
        if (buckets.size() != servers.length) {
            throw new RuntimeException("There was a hash conflict");
        }
        //System.out.println(buckets);
    }

    /**
     * key 按顺时针方向路由到第一个bucket
     *
     * @param key
     * @return
     */
    public static String routeToServer(String key) {

        int fromKey  = HashFunctions.fnv1_32_hash(key);
        // 返回 <= fromKey的子集
        SortedMap<Integer, String> set = buckets.tailMap(fromKey);
        if (set.isEmpty()) {
            return buckets.get(buckets.firstKey());
        }
        return set.get(set.firstKey());
    }
}
