package edu.westga.cs3152.model;

/**
 * Keeps track of two values
 * @author cvoll
 * @version Fall 2021
 * @param <K> first value
 * @param <V> second Value
 */
public class Tuple<K, V> {
	private K first;
	private V second;
	
	/**
	 * Creates a tuple instance with user entered values
	 * @param first the first value
	 * @param second the second value
	 */
	public Tuple(K first, V second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Gets the first value of the tuple
	 * @return the first value of the tuple
	 */
	public K getFirst() {
		return this.first;
	}
	
	/**
	 * Gets the second value of the tuple
	 * @return the second value ofthe tuple
	 */
	public V getSecond() {
		return this.second;
	}
	
}
