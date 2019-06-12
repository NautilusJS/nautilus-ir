package com.mindlin.nautilus.impl.util;

import java.io.Reader;
import java.nio.InvalidMarkException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ProfilingReaderCharacterStream extends ReaderCharacterStream {
	private final Statistics stats = new Statistics();
	
	public ProfilingReaderCharacterStream(Reader in) {
		super(in);
	}
	
	@Override
	public AbstractCharacterStream mark() {
		stats.markCount++;
		return super.mark();
	}
	
	@Override
	public AbstractCharacterStream unmark() throws InvalidMarkException {
		this.stats.unmarkCount++;
		return super.unmark();
	}
	
	@Override
	public ReaderCharacterStream resetToMark() throws InvalidMarkException {
		this.stats.resetToMarkCount++;
		return super.resetToMark();
	}
	
	@Override
	public String copyFromMark() {
		this.stats.copyFromMarkCount++;
		String result = super.copyFromMark();
		this.stats.copyFromMarkLen.put(result.length());
		return result;
	}
	
	public static class Statistics {
		public static Statistics merge(Collection<Statistics> values) {
			Statistics result = new Statistics();
			for (Statistics value : values) {
				result.bufferResizes += value.bufferResizes;
				result.markCount += value.markCount;
				result.unmarkCount += value.unmarkCount;
				result.resetToMarkCount += value.resetToMarkCount;
				result.copyFromMarkCount += value.copyFromMarkCount;
			}
			
			result.copyFromMarkLen = Histogram.merge(values.stream().map(v -> v.copyFromMarkLen).collect(Collectors.toList()));
			
			return result;
		}
		public long bufferResizes = 0;
		/** Number of calls to mark() */
		public long markCount = 0;
		/** Number of calls to unmark() */
		public long unmarkCount = 0;
		/** Number of calls to resetToMark() */
		public long resetToMarkCount = 0;
		/** Number of calls to copyFromMark() */
		public long copyFromMarkCount = 0;
		/** Histogram of length when calling copyFromMark() */
		public Histogram copyFromMarkLen = new Histogram();
	}
	
	public static class Histogram {
		public static Histogram merge(Histogram...values) {
			return Histogram.merge(Arrays.asList(values));
		}
		
		public static Histogram merge(Collection<Histogram> values) {
			int len = values.stream()
					.mapToInt(Histogram::count)
					.sum();
			Histogram result = new Histogram();
			result.values = new int[len];
			
			int idx = 0;
			for (Histogram value : values) {
				System.arraycopy(value.values, 0, result.values, idx, value.count());
				idx += value.count();
			}
			result.size = idx;
			
			return result;
		}
		
		private int[] values = new int[16];
		private int size;
		
		public synchronized void put(int value) {
			if (this.size > values.length)
				values = Arrays.copyOf(this.values, Math.multiplyExact(this.values.length, 2));
			values[size++] = value;
		}
		
		public int count() {
			return this.size;
		}
		
		public int getMinimum() {
			return Arrays.stream(values).min().getAsInt();
		}
		
		public int getMaximum() {
			return Arrays.stream(values).max().getAsInt();
		}
		
		public double getMean() {
			return Arrays.stream(values).average().orElseGet(() -> Double.NaN);
		}
	}
	
}
