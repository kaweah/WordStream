package com.kaweah.data;

import java.util.ArrayList;
import java.util.List;

public abstract class BinaryTree <T> implements Comparable <T> {
	// Members
	// Self-referential data structure.
	protected BinaryTree <T> left;
	protected BinaryTree <T> right;
	protected T value;
	
	// Constructors
	
	public BinaryTree(T v) {
		value = v;
	}

	public BinaryTree(List <T> data) {
	}

	// Modifiers
	
	public BinaryTree <T> addLeftNode(BinaryTree <T> bt) {
		left = bt;
		return left;
	}

	public BinaryTree <T> addRightNode(BinaryTree <T> bt) {
		right = bt;
		return right;
	}
	
	public void setValue(T v) {
		value = v;
	}
	
	// Accessors
	
	public T getValue() {
		return value;
	}
	
	public BinaryTree <T> getLeft() {
		return left;
	}

	public BinaryTree <T> getRight() {
		return right;
	}

	// Fuctions
		
	private void extractData(List<T> results) {
		if (left != null) {
			left.extractData(results);			
		}
		results.add(value);
		if (right != null) {
			right.extractData(results);
		}
	}
	
	protected abstract void populate(List<T> data);
	
	public void balance() {
		List<T> data = new ArrayList<>();
		extractData(data);
		populate(data);
	}

	protected abstract void insertOrdered(T v);

	protected abstract BinaryTreeOfIntegers seek(T v);

	protected abstract T getPath(StringBuffer buffer, T v);
	
}
