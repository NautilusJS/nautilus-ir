package com.mindlin.nautilus.impl.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class RecursiveMap<K, V> implements Map<K, V> {
	protected final Map<K, V> parent;
	transient int modCount;
	
	Map<K, V> backing;
	
	public RecursiveMap() {
		this(null, new HashMap<>());
	}
	
	public RecursiveMap(Map<K, V> parent) {
		this(parent, new HashMap<>());
	}
	
	public RecursiveMap(Map<K, V> parent, Map<K, V> backing) {
		this.parent = parent;
		this.backing = backing;
	}
	
	@Override
	public int size() {
		return this.backing.size() + (this.parent == null ? 0 : this.parent.size());
	}
	
	@Override
	public boolean isEmpty() {
		return this.isEmpty() && (this.parent == null || this.parent.isEmpty());
	}
	
	@Override
	public boolean containsKey(Object key) {
		return this.backing.containsKey(key) || (this.parent != null && this.parent.containsKey(key));
	}
	
	@Override
	public boolean containsValue(Object value) {
		return this.backing.containsKey(value) || (this.parent != null && this.parent.containsKey(value));
	}
	
	@Override
	@SuppressWarnings("null")
	public V get(Object key) {
		if (this.backing.containsKey(key))
			return this.backing.get(key);
		if (this.parent != null)
			return this.parent.get(key);
		return null;
	}
	
	protected Optional<V> putIfIn(K key, V value) {
		if (this.backing.containsKey(key)) {
			V result = this.backing.put(key, value);
			if (result != value)
				this.modCount++;
			return Optional.of(result);
		}
		if (this.parent != null) {
			if (this.parent instanceof RecursiveMap) {
				// Optimal
				Optional<V> result = ((RecursiveMap<K, V>) this.parent).putIfIn(key, value);
				if (result.isPresent() && result.get() != value)
					this.modCount++;
				//TODO: we can reduce recursion here
				return result;
			} else {
				if (this.parent.containsKey(key))
					return Optional.of(this.parent.put(key, value));
			}
		}
		return Optional.empty();
	}
	
	@Override
	public V put(K key, V value) {
		Optional<V> r = this.putIfIn(key, value);
		if (r.isPresent())
			return r.get();
		this.modCount++;
		return this.backing.put(key, value);
	}
	
	public V putLocal(K key, V value) {
		V result = this.backing.put(key, value);
		if (result != value)
			this.modCount++;
		return result;
	}
	
	public void putAllLocal(Map<? extends K, ? extends V> m) {
		this.backing.putAll(m);
	}
	
	@Override
	@SuppressWarnings("null")
	public V remove(Object key) {
		if (this.containsKey(key))
			return this.remove(key);
		if (this.parent != null)
			return this.parent.remove(key);
		return null;
	}
	
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		m.forEach(this::put);
	}
	
	@Override
	public void clear() {
		this.modCount++;
		this.backing.clear();
		if (this.parent != null)
			this.parent.clear();
	}
	
	@Override
	public Set<K> keySet() {
		Set<K> result = new HashSet<>();
		result.addAll(this.backing.keySet());
		if (this.parent != null)
			result.addAll(this.parent.keySet());
		return result;
	}
	
	@Override
	public Collection<V> values() {
		//Not super useful for our purposes
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Set<Entry<K, V>> entrySet() {
		// Not super useful for our purposes
		throw new UnsupportedOperationException();
	}
	
}
