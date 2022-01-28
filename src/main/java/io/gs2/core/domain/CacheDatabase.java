package io.gs2.core.domain;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CacheDatabase {

    private final Map<Class<?>, Map<String, Map<String, Pair<Object, Long>>>> cache = new HashMap<>();
    private final Map<Class<?>, Map<String, Boolean>> listCached = new HashMap<>();

    public void clear() {
        cache.clear();
        listCached.clear();
    }

    public <T> boolean isListCached(String parentKey, Class<T> clazz) {
        if (!listCached.containsKey(clazz)) {
            listCached.put(clazz, new HashMap<>());
        }
        return listCached.get(clazz).containsKey(parentKey);
    }

    public <T> void listCached(String parentKey, Class<T> clazz) {
        if (!listCached.containsKey(clazz)) {
            listCached.put(clazz, new HashMap<>());
        }
        listCached.get(clazz).put(parentKey, true);
    }

    public <T> void listCacheClear(String parentKey, Class<T> clazz) {
        if (!listCached.containsKey(clazz)) {
            listCached.put(clazz, new HashMap<>());
        }
        listCached.get(clazz).remove(parentKey);
    }

    public <T> void put(String parentKey, String key, T obj, long ttl) {
        if (!cache.containsKey(obj.getClass())) {
            cache.put(obj.getClass(), new HashMap<>());
        }
        if (!cache.get(obj.getClass()).containsKey(parentKey)) {
            cache.get(obj.getClass()).put(parentKey, new HashMap<>());
        }
        if (ttl == 0) {
            ttl = System.currentTimeMillis() + 1000 * 60 * Gs2.defaultCacheMinutes;
        }
        cache.get(obj.getClass()).get(parentKey).put(key, Pair.of(obj, ttl));
    }

    public <T> void delete(String parentKey, String key, Class<T> clazz) {
        if (!cache.containsKey(clazz)) {
            cache.put(clazz, new HashMap<>());
        }
        if (!cache.get(clazz).containsKey(parentKey)) {
            cache.get(clazz).put(parentKey, new HashMap<>());
        }
        cache.get(clazz).get(parentKey).remove(key);
    }

    public <T> T get(String parentKey, String key, Class<T> clazz) {
        if (!cache.containsKey(clazz)) {
            cache.put(clazz, new HashMap<>());
        }
        if (!cache.get(clazz).containsKey(parentKey)) {
            cache.get(clazz).put(parentKey, new HashMap<>());
        }
        if (cache.get(clazz).get(parentKey).containsKey(key)) {
            Pair<Object, Long> entry = cache.get(clazz).get(parentKey).get(key);
            Object obj = entry.getLeft();
            Long ttl = entry.getRight();
            if (ttl < System.currentTimeMillis()) {
                delete(parentKey, key, clazz);
                return null;
            }
            return (T) obj;
        } else {
            return null;
        }
    }

    public <T> List<T> list(String parentKey, Class<T> clazz) {
        if (!cache.containsKey(clazz)) {
            cache.put(clazz, new HashMap<>());
        }
        if (!cache.get(clazz).containsKey(parentKey)) {
            cache.get(clazz).put(parentKey, new HashMap<>());
        }
        return cache.get(clazz).get(parentKey).values().stream().map(v -> (T) v.getLeft()).collect(Collectors.toList());
    }
}
