package com.stellariver.example.com.stellariver.dumpling;

import com.sun.tools.javac.util.Assert;

import java.util.*;

public class MortalMap<K, V> extends HashMap<K, V> {

    public static final long DEFAULT_LIVE = 100L;

    private final PriorityQueue<LiveKey<K>> queue = new PriorityQueue<>();

    @Override
    public V put(K key, V value) {
        return put(key, value, DEFAULT_LIVE);
    }

    public V put(K key, V value, long liveMillis) {
        Assert.checkNonNull(key);
        Assert.checkNonNull(value);
        Assert.check(liveMillis > 10, "live time must bigger than 10 mill seconds at least");

        long currentTimeMillis = System.currentTimeMillis();
        V putResult = super.put(key, value);
        queue.add(new LiveKey<>(key, currentTimeMillis + liveMillis));
        removeDeath(currentTimeMillis);

        return putResult;
    }

    private void removeDeath(long currentTimeMillis) {
        LiveKey<K> oldestLiveKey;
        while (true) {
            oldestLiveKey  = queue.peek();
            if (oldestLiveKey == null || oldestLiveKey.deathMillis > currentTimeMillis) {
                break;
            }
            LiveKey<K> removeLiveKey = queue.remove();
            super.remove(removeLiveKey.key);
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
       super.putAll(m);
        long currentTimeMillis = System.currentTimeMillis();
        m.forEach((k, v) -> queue.add(new LiveKey<>(k, currentTimeMillis + DEFAULT_LIVE)));
       removeDeath(System.currentTimeMillis());
    }

    @Override
    public void clear() {
        super.clear();
        queue.clear();
    }

    static private class LiveKey<K> implements Comparable<LiveKey<K>>{

        private final K key;

        private final long deathMillis;

        public LiveKey(K key, long deathMillis) {
            this.key = key;
            this.deathMillis = deathMillis;
        }

        @Override
        public int compareTo(LiveKey o) {
            return Long.compare(this.deathMillis, o.deathMillis);
        }

    }
}
