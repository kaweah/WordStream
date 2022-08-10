package com.kaweah.data;

/**
 * Self-referential singly-linked list
 * 
 * @author Dan Jensen (Kaweah)
 *
 * @param <T>
 */

public class LinkedList <T> {

	// Member data
	
	private LinkedList <T> next;
	private T value;

	// Constructors
	// Don't use <T> in naming constructors of generic class
	
	public LinkedList (T v) {
		value = v;
	}
	
	// Modifiers
	
	public LinkedList <T> setNext(LinkedList <T> dll) {
		next = dll;
		return next;
	}
	
	public void setValue(T v) {
		value = v;
	}
	
	// Accessors
		
	public LinkedList <T> getNext() {
		return next;
	}
	
	public T getValue() {
		return value;
	}
		
	// Fuctions
	
	// Insert a node

	public LinkedList <T> insert(Character v) {
		if (value == v) return null;
		else if (next == null) {
			next = new LinkedList(v);
			return next;
		} else return next.insert(v);
	}
	
	// Seek a node by key
	
	public LinkedList <T> seekNext(T v) {		
		if (value == v) return this;
		else if (next == null) {
			return null;
		} else return seekNext(v);
	}
	
	/** Customize recursive seek function to build search path string.
	 * 
	 * @param buffer
	 * @param v
	 * @return search result (null if not found)
	 */
	
	public LinkedList <T> getPath(StringBuffer buffer, T v) {
		
		buffer.append("" + value);
		
		if (value == v) return this;
		else if (next == null) {
			buffer.append("> NOT FOUND");
			return null;
		} else {
			buffer.append(">");
			return next.getPath(buffer, v);
		}
	}
	
	public static void main (String[] args) {
		LinkedList <Character> root = new LinkedList <> ('a');
		try {
			System.out.println("insert('e') returned " + root.insert('e'));
			// System.out.println("insertOrdered('e') returned " + root.insertOrdered('e'));
			System.out.println("insert('e') returned " + root.insert('e'));
		} catch (Exception x) {
			System.err.println(x.getMessage());
		}
		StringBuffer buffer = new StringBuffer();
		System.out.println("getPath('f') found " + root.getPath(buffer, 'f'));
		System.out.println(buffer.toString());
		buffer = new StringBuffer();
		System.out.println("getPath('e') found " + root.getPath(buffer, 'e'));
		System.out.println(buffer.toString());
	}

}
