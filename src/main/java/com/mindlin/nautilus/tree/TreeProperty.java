package com.mindlin.nautilus.tree;

import java.io.Serializable;
import java.util.Objects;

import com.mindlin.nautilus.util.ObjectCache;

@SuppressWarnings("unused")
public class TreeProperty<T> implements Serializable {
	private static final long serialVersionUID = 2807591757585850286L;
	static ObjectCache<TreeProperty<?>> cache = new ObjectCache<>();
	
	@SuppressWarnings("unchecked")
	public static <T> TreeProperty<T> get(String name) {
		return (TreeProperty<T>) cache.intern(new TreeProperty<>(name));
	}
	
	private final String name;
	
	private TreeProperty(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.name);
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof TreeProperty) && Objects.equals(((TreeProperty<?>) obj).name, this.name);
	}
}
