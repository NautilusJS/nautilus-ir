package com.mindlin.nautilus.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Histogram<T> {
	ConcurrentMap<T, Integer> delegate = new ConcurrentHashMap<>();
	
	public void mark(T value) {
		mark(value, 1);
	}
	
	public void mark(T value, int hits) {
		delegate.compute(value, (k, prev) -> (prev == null ? hits : prev + hits));
	}
}
