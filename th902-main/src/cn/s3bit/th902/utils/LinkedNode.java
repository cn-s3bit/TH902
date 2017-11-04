package cn.s3bit.th902.utils;

public class LinkedNode<T> {
	/**
	 * Data on this node.
	 */
	public T data;
	/**
	 * Next node.
	 */
	public LinkedNode<T> next = null;
	/**
	 * Previous node.
	 */
	public LinkedNode<T> previous = null;
	
	public LinkedNode() {
		this.data = null;
	}
	
	public LinkedNode(T data) {
		this.data = data;
	}
	
	/**
	 * @param val Data to insert
	 * @return The inserted node.
	 */
	public LinkedNode<T> insertAfter(T val) {
		LinkedNode<T> node = new LinkedNode<>();
		node.data = val;
		node.next = next;
		node.previous = this;
		if (next != null) next.previous = node;
		next = node;
		return node;
	}
	/**
	 * @param val Data to insert
	 * @return The inserted node.
	 */
	public LinkedNode<T> insertBefore(T val) {
		LinkedNode<T> node = new LinkedNode<>();
		node.data = val;
		node.previous = previous;
		node.next = this;
		if (previous != null) previous.next = node;
		previous = node;
		return node;
	}
	
	public void remove() {
		if (previous != null) previous.next = next;
		if (next != null) next.previous = previous;
	}
}
