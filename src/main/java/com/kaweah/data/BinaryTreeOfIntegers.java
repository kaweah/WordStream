package com.kaweah.data;

import java.util.List;

public class BinaryTreeOfIntegers extends BinaryTree <Integer> {
	
	// Constructors
	
	public BinaryTreeOfIntegers(Integer v) {
		super(v);
	}

	/**
	 * Create a balanced binary tree from an ordered list
	 * 
	 * @param data an ordered list
	 */
	
	public BinaryTreeOfIntegers(List<Integer> data) {
		super(data);
		// split data list
		int midpoint = data.size();
		List<Integer> data_left = data.subList(0, midpoint-1);
		List<Integer> data_right = data.subList(midpoint+1, data.size()-1);
		value = data.get(midpoint);
		left = new BinaryTreeOfIntegers(data_left);
		left = new BinaryTreeOfIntegers(data_right);		
	}
/*
	public BinaryTree(BinaryTree lt, BinaryTree rt) {
		left = lt;
		right = rt;
	}
*/	
	
	// Interface implementation
	// If the value of this node is great than the passed value, then the result is positive
	
	public int compareTo(Integer v) {
		if (value < v) return -1;
		else if (value == v) return 0;
		else return 1;
	}
	
	// Fuctions
	
	// Insert a node in order (recursive)
	
	public void insertOrdered(Integer v) {
		int relation = compareTo(v);		
		if (relation < 0) {
			if (right == null) {
				right = new BinaryTreeOfIntegers(v);
			} else right.insertOrdered(v);
		} else if (relation > 0) {
			if (left == null) {
				left = new BinaryTreeOfIntegers(v);
			} else left.insertOrdered(v);
		} // ignore if equal (enforce uniqueness)
	}
	
	// Seek a node by key
	// Tree must be ordered
	
	public BinaryTreeOfIntegers seek(Integer v) {
		int relation = value.compareTo(v);
		if (relation < 0) {
			if (right == null) return null;
			else return right.seek(v);
		} else if (relation > 0) {
			if (left == null) return null;
			else return left.seek(v);
		} return this; // (relation == 0)
	}
		
	protected void populate(List<Integer> data) {
		// split data list
		int midpoint = data.size();
		List<Integer> data_left = data.subList(0, midpoint-1);
		List<Integer> data_right = data.subList(midpoint+1, data.size()-1);
		value = data.get(midpoint);
		left = null; right = null;
		left = new BinaryTreeOfIntegers(data_left);
		left = new BinaryTreeOfIntegers(data_right);
	}
	
	/** Customize recursive seek function to build search path string.
	 * 
	 * @param buffer
	 * @param v
	 * @return search result (null if not found)
	 */
	
	public Integer getPath(StringBuffer buffer, Integer v) {
		
		buffer.append("" + value);
		
		if (v > value) {
			if (right == null) {
				buffer.append("> NOT FOUND");
				return null;
			}
			else {
				buffer.append(">");
				return right.getPath(buffer, v);
			}
		} else if (v < value) {
			if (left == null) {
				buffer.append("> NOT FOUND");
				return null;
			}
			else {
				buffer.append(">");
				return left.getPath(buffer, v);
			}
		} return v;
	}
		
	public static void main (String[] args) {
		BinaryTreeOfIntegers root = new BinaryTreeOfIntegers(5);
		root.insertOrdered(7);
		root.insertOrdered(6);
		StringBuffer buffer = new StringBuffer();
		System.out.println("getPath(7) found " + root.getPath(buffer, 7));
		System.out.println(buffer.toString());
		buffer = new StringBuffer();
		System.out.println("getPath(6) found " + root.getPath(buffer, 6));
		System.out.println(buffer.toString());
		buffer = new StringBuffer();
		System.out.println("getPath(8) found " + root.getPath(buffer, 8));
		System.out.println(buffer.toString());
	}
}
