package com.me.mygdxgame.Manager;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A simple implementation of an LRU cache.
 * <p>
 * Retrieved from <a href=
 * "http://stackoverflow.com/questions/224868/easy-simple-to-use-lru-cache-in-java"
 * >Stackoverflow</a>.
 * &&
 * https://code.google.com/p/steigert-libgdx/source/browse/tags/post-20120709/
 * tyrian-game/src/com/blogspot/steigert/tyrian/utils/LRUCache.java
 */
public class LRU<K, V> {
	/**
	 * Called when a cached element is about to be removed.
	 */
	public interface CacheEntryRemovedListener<K, V> {
		void notifyEntryRemoved(K key, V value);
	}

	private Map<K, V> cache;
	private CacheEntryRemovedListener<K, V> entryRemovedListener;

	/**
	 * Creates the cache with the specified max entries.
	 */
	public LRU(final int maxEntries) {
		cache = new LinkedHashMap<K, V>(maxEntries + 1, .75F, true) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8761089568232602010L;

			public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				if (size() > maxEntries) {
					if (entryRemovedListener != null) {
						entryRemovedListener.notifyEntryRemoved(
								eldest.getKey(), eldest.getValue());
					}
					return true;
				}
				return false;
			}
		};
	}

	public void add(K key, V value) {
		cache.put(key, value);
	}

	public V get(K key) {
		return cache.get(key);
	}

	public Collection<V> retrieveAll() {
		return cache.values();
	}

	public void setEntryRemovedListener(
			CacheEntryRemovedListener<K, V> entryRemovedListener) {
		this.entryRemovedListener = entryRemovedListener;
	}
}
