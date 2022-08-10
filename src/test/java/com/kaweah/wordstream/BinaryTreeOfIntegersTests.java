package com.kaweah.wordstream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.kaweah.data.BinaryTree;
import com.kaweah.data.BinaryTreeOfIntegers;

@SpringBootTest
public class BinaryTreeOfIntegersTests {

    Logger log = Logger.getLogger(this.getClass().getName());

	@Test
	@Transactional
	public void testPersistWordInLanguage() {
		log.info("... testPersistBook ...");

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
	
	@Test
	public void testTree() {
		BinaryTreeOfIntegers root = new BinaryTreeOfIntegers(5);
		root.insertOrdered(7);
		root.insertOrdered(6);
		
		assertNull(root.getLeft());

		BinaryTreeOfIntegers rightNode = (BinaryTreeOfIntegers) root.getRight();
		Integer actualValue = rightNode.getValue();
		assertEquals("Unexpected value: " + actualValue, (Integer)7, actualValue);
		
		BinaryTreeOfIntegers leftNode = (BinaryTreeOfIntegers) rightNode.getLeft();
		actualValue = leftNode.getValue();
		assertEquals("Unexpected value: " + actualValue, (Integer)6, actualValue);
		
		assertNull(rightNode.getRight());
		
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
