package org.mac.sample.algorithm.hash.rendezvous;

import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListSet;

import com.google.common.hash.Funnel;
import com.google.common.hash.HashFunction;

/**
 * <p>A high performance thread safe implementation of Rendezvous (Highest Random Weight, HRW) hashing is an algorithm that allows clients to achieve distributed agreement on which node (or proxy) a given
 * key is to be placed in. This implementation has the following properties.
 * <ul>
 * <li>
 *     非阻塞读：确定密key于哪个节点始终是非阻塞的
 *     Non-blocking reads : Determining which node a key belongs to is always non-blocking.
 *     添加和删除节点会阻塞
 *     Adding and removing nodes however blocks each other
 * </li>
 * <li>
 *     低开销：使用低开销的hash函数
 *     Low overhead: providing using a hash function of low overhead
 * </li>
 * <li>
 *     负载均衡：由于散列函数是随机的，所以n个节点中的每一个都同样可能接收K.
 *     Load balancing: Since the hash function is randomizing, each of the n nodes is equally likely to receive the key K.
 *     Loads are uniform across the sites.
 * </li>
 * <li>
 *     高命中：由于所有客户端都确定将指定key放入同一节点N，因此每次获取或将K放入Node会产生命中率方面的最大效用。
 *     High hit rate: Since all clients agree on placing an key K into the same node N ,
 *     each fetch or placement of K into N yields the maximum utility in terms of hit rate.
 *     除非被Node的某些替换算法驱逐，否则将始终找到K.
 *     The key K will always be found unless it is evicted by some replacement algorithm at N.
 * </li>
 * <li>
 *     最小的击穿：删除节点时，只需映射到该节点的键重新映射
 *     Minimal disruption: When a node is removed, only the keys mapped to that node need to be remapped and they will be distributed evenly
 * </li>
 * </ul>
 * </p>
 * source: https://en.wikipedia.org/wiki/Rendezvous_hashing
 *
 * @author Chris Lohfink
 *
 * @param <K>
 *            type of key
 * @param <N>
 *            type node/site or whatever want to be returned (ie IP address or String)
 */
public class RendezvousHash<K, N extends Comparable<? super N>> {

    /**
     * A hashing function from guava, ie Hashing.murmur3_128()
     */
    private final HashFunction hasher;

    /**
     * A funnel to describe how to take the key and add it to a hash.
     *
     * @see com.google.common.hash.Funnel
     */
    private final Funnel<K> keyFunnel;

    /**
     * Funnel describing how to take the type of the node and add it to a hash
     *
     *  eg.
     *  public enum PersonFunnel implements Funnel<Person> {
     *     INSTANCE;
     *     public void funnel(Person person, PrimitiveSink into) {
     *       into.putUnencodedChars(person.getFirstName())
     *           .putUnencodedChars(person.getLastName())
     *           .putInt(person.getAge());
     *     }
     *  }
     */
    private final Funnel<N> nodeFunnel;

    /**
     * All the current nodes in the pool
     */
    private final ConcurrentSkipListSet<N> ordered;

    /**
     * Creates a new RendezvousHash with a starting set of nodes provided by init. The funnels will be used when generating the hash that combines the nodes and
     * keys. The hasher specifies the hashing algorithm to use.
     */
    public RendezvousHash(HashFunction hasher, Funnel<K> keyFunnel, Funnel<N> nodeFunnel, Collection<N> init) {
        if (hasher == null) throw new NullPointerException("hasher");
        if (keyFunnel == null) throw new NullPointerException("keyFunnel");
        if (nodeFunnel == null) throw new NullPointerException("nodeFunnel");
        if (init == null) throw new NullPointerException("init");
        this.hasher = hasher;
        this.keyFunnel = keyFunnel;
        this.nodeFunnel = nodeFunnel;
        this.ordered = new ConcurrentSkipListSet<N>(init);
    }

    /**
     * Removes a node from the pool. Keys that referenced it should after this be evenly distributed amongst the other nodes
     *
     * @return true if the node was in the pool
     */
    public boolean remove(N node) {
        return ordered.remove(node);
    }

    /**
     * Add a new node to pool and take an even distribution of the load off existing nodes
     *
     * @return true if node did not previously exist in pool
     */
    public boolean add(N node) {
        return ordered.add(node);
    }

    /**
     * return a node for a given key
     */
    public N get(K key) {
        long maxValue = Long.MIN_VALUE;
        N max = null;
        for (N node : ordered) {
            long nodesHash = hasher.newHasher()
                    .putObject(key, keyFunnel)
                    .putObject(node, nodeFunnel)
                    .hash().asLong();
            if (nodesHash > maxValue) {
                max = node;
                maxValue = nodesHash;
            }
        }
        return max;
    }
}
