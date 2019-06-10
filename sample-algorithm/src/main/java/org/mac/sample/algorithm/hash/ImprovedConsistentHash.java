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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 使用增加虚拟节点的方法改进一致性hash
 *
 * @auther mac
 * @date 2018-12-26
 */
public class ImprovedConsistentHash {
    /**
     * 服务器节点
     */
    private static final List<String> realServerNodes = new LinkedList<String>(Arrays.asList(
            "192.168.36.100:6379",
            "192.168.36.101:6379",
            "192.168.36.102:6379",
            "192.168.36.103:6379",
            "192.168.36.104:6379"
            )
    );

    /**key表示服务器的hash值，value表示服务器的名称*/
    private static final SortedMap<Integer, String> virtualBuckets =  new TreeMap<Integer, String>();


    /** 每个真实节点对应的虚拟节点 (800 - K * REAL_NODES): 一个真实节点应对应的虚拟节点数的一个大致的函数关系*/
    //private static final int K = 100;

    private static final int VIRTUAL_NODES = 200 ;
    private static final String SEPARATOR = "-";

    static {
        for (String realNode : realServerNodes) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String virtualNodeName = "VN["+i+"]"+SEPARATOR+realNode;
                int hash = HashFunctions.fnv1_32_hash(virtualNodeName);
                virtualBuckets.put(hash, virtualNodeName);
            }
        }
        //System.out.println(virtualBuckets);
    }

    /**
     * key 按顺时针方向路由到第一个bucket
     *
     * @param key
     * @return
     */
    public static String routeToServer(String key) {

        int hash  = HashFunctions.fnv1_32_hash(key);
        SortedMap<Integer, String> set = virtualBuckets.tailMap(hash);

        String firstNode = set.isEmpty()? virtualBuckets.get(virtualBuckets.firstKey()) : set.get(set.firstKey());

        return firstNode.substring(firstNode.indexOf(SEPARATOR)+1);
    }
}
