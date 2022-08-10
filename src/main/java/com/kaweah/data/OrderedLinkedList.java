package com.kaweah.data;

public class OrderedLinkedList implements Comparable <Character> {

	public OrderedLinkedList (Character v) {
		value = v;
	}
	
	// Modifiers
	
	public OrderedLinkedList setNext(OrderedLinkedList dll) {
		next = dll;
		return next;
	}
	
	public void setValue(Character v) {
		value = v;
	}
	
	// Accessors
		
	public OrderedLinkedList getNext() {
		return next;
	}
	
	public Character getValue() {
		return value;
	}
	
	// Interface implementation
	
	public int compareTo(Character v) {
		if (value < v) return -1;
		else if (value == v) return 0;
		else return 1;
	}
	
	// Fuctions
	
	// Insert a node

	public OrderedLinkedList insert(Character v) {
		if (value == v) return null;
		else if (next == null) {
			next = new OrderedLinkedList(v);
			return next;
		} else return next.insert(v);
	}

	public OrderedLinkedList insertOrdered(Character v) throws Exception {
		int relation = compareTo(v);		
		if (relation < 0) {
			// this node's value is less that specified value: continue
			if (next == null) {
				next = new OrderedLinkedList(v);
				return next;
			} else if (next.getValue() > v) {
				OrderedLinkedList grandchild = next;
				next = new OrderedLinkedList(v);
				next.setNext(grandchild);
				return next;
			} else {
				return next.insertOrdered(v);
			}
		} else if (relation > 0) {
			// this node's value is bigger that specified value (error)
			throw new Exception("Unexpected flow.");
		} return null; // ignore if equal (enforce uniqueness)
	}
	
	// Seek a node by key
	
	public OrderedLinkedList seekNext(Character v) {		
		if (value == v) return this;
		else if (next == null) {
			return null;
		} else return seekNext(v);
	}

	public OrderedLinkedList seekNextOrdered(Character v) {
		int relation = value.compareTo(v);
		if (relation < 0) {
			// this node's value is less that specified value: continue
			if (next == null) return null;
			return next.seekNextOrdered(v);
		} else if (relation > 0) {
			// this node's value is bigger that specified value: passed
			return null;
		} return this;
	}
	
	/** Customize recursive seek function to build search path string.
	 * 
	 * @param buffer
	 * @param v
	 * @return search result (null if not found)
	 */
	
	public OrderedLinkedList getPath(StringBuffer buffer, Character v) {
		
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

	public OrderedLinkedList getPathOrdered(StringBuffer buffer, Character v) {
		
		buffer.append("" + value);
		
		int relation = value.compareTo(v);
		if (relation < 0) {
			// this node's value is less that specified value: continue
			if (next == null) {
				buffer.append("> NOT FOUND");
				return null;
			} else {
				buffer.append(">");
				return next.getPathOrdered(buffer, v);				
			}
		} else if (relation > 0) {
			// this node's value is bigger that specified value: passed
			buffer.append("> NOT FOUND");
			return null;
		} return this;		
	}

	private OrderedLinkedList next;
	private Character value;
	
	public static void main (String[] args) {
		OrderedLinkedList root = new OrderedLinkedList('a');
		try {
			System.out.println("insertOrdered('f') returned " + root.insertOrdered('f'));
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
