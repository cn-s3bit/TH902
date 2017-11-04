package cn.s3bit.th902.utils;

public class LinkedNode<T> {
	/**
	 * Data on this node.
	 */
	public T data;
	/**
	 * Next node.
	 */
	public LinkedNode<T> next;
	/**
	 * Previous node.
	 */
	public LinkedNode<T> previous;
	
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
		next.previous = node;
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
		previous.next = node;
		previous = node;
		return node;
	}
	
	public void remove() {
		previous.next = next;
		next.previous = previous;
	}
}
