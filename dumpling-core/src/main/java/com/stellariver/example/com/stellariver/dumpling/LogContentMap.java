package com.stellariver.example.com.stellariver.dumpling;

import com.sun.tools.javac.util.Assert;

import java.util.*;

public class LogContentMap extends HashMap<String, String> {

    private final PriorityQueue<LiveKey> queue = new PriorityQueue<>();

    @Override
    public String put(String key, String value) {
        return put(key, value, 100);
    }

    public String put(String key, String value, long liveMillis) {
        Assert.checkNonNull(key);
        Assert.checkNonNull(value);
        Assert.check(liveMillis > 10, "live time must bigger than 10 mill seconds at least");

        long currentTimeMillis = System.currentTimeMillis();
        String putResult = super.put(key, value);
        queue.add(new LiveKey(key, currentTimeMillis + liveMillis));
        removeDeath(currentTimeMillis);

        return putResult;
    }

    private void removeDeath(long currentTimeMillis) {
        LiveKey oldestLiveKey;
        while (true) {
            oldestLiveKey  = queue.peek();
            if (oldestLiveKey == null || oldestLiveKey.deathMillis > currentTimeMillis) {
                break;
            }
            LiveKey removeLiveKey = queue.remove();
            super.remove(removeLiveKey.key);
        }
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
       super.putAll(m);
       removeDeath(System.currentTimeMillis());
    }

    @Override
    public void clear() {
        super.clear();
        queue.clear();
    }

    static private class LiveKey implements Comparable<LiveKey>{

        private final String key;

        private final long deathMillis;

        public LiveKey(String key, long deathMillis) {
            this.key = key;
            this.deathMillis = deathMillis;
        }

        @Override
        public int compareTo(LiveKey o) {
            return Long.compare(this.deathMillis, o.deathMillis);
        }

    }
}
