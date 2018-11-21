package assignment11;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class PriorityQueueTesting{

	@Test(expected = NoSuchElementException.class)
	public void testDeleteEmpty() {
		PriorityQueue<Integer> queue1 = new PriorityQueue<>();
		queue1.clear();
		queue1.deleteMin();
	}

	@Test(expected = NoSuchElementException.class)
	public void testFindMinWhileEmpty() {
		PriorityQueue<Integer> queue1 = new PriorityQueue<>();
		queue1.clear();
		queue1.findMin();

	}

	@Test
	public void testAdd() {
		PriorityQueue<Integer> queue1 = new PriorityQueue<>();

		queue1.add(10);
		queue1.add(4);
		queue1.add(6);
		queue1.add(11);
		queue1.generateDotFile("smallTest.dot");
		assertEquals(true, queue1.findMin().equals(4));
		queue1.deleteMin();
		assertEquals(false, queue1.findMin().equals(4));
		queue1.deleteMin();
		assertEquals(true, queue1.findMin().equals(10));
	}

	@Test
	public void testDeleteMin() {
		PriorityQueue<Integer> testing = new PriorityQueue<>();
		testing.add(-4);
		testing.add(10);
		testing.add(-89);
		testing.add(98);
		testing.add(34);
		testing.add(123);
		testing.add(-687);
		testing.add(95);
		testing.add(12);
		testing.add(234);
		testing.add(2);
		assertEquals(true, testing.findMin().equals(-687));
		assertEquals(true, testing.deleteMin().equals(-687));
		testing.deleteMin();
		assertEquals(true, testing.findMin().equals(-4));
		testing.generateDotFile("MediumTest.dot");
	}

	@Test(expected = NoSuchElementException.class)
	public void findMinOnClearedArray() {
		PriorityQueue<Integer> queue1 = new PriorityQueue<>();

		queue1.add(10);
		queue1.add(4);
		queue1.add(6);
		queue1.add(11);
		queue1.clear();
		queue1.findMin();
	}

	@Test
	public void clearWholeArrayAdd() {
		PriorityQueue<Integer> queue1 = new PriorityQueue<>();

		queue1.add(10);
		queue1.add(4);
		queue1.add(6);
		queue1.add(11);
		queue1.clear();
		queue1.add(0);
		assertEquals(true, queue1.findMin().equals(0));
	}

	@Test
	public void clearWholeArrayDeleteMin() {
		PriorityQueue<Integer> queue1 = new PriorityQueue<>();

		queue1.add(10);
		queue1.add(4);
		queue1.add(6);
		queue1.add(11);
		queue1.clear();
		queue1.add(132);
		queue1.deleteMin();
		assertEquals(0, queue1.size());
	}

}
