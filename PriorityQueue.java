package assignment11;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.Comparator;

import java.util.NoSuchElementException;

/**
 * 
 * Represents a priority queue of generically-typed items. The queue is
 * 
 * implemented as a min heap. The min heap is implemented implicitly as an
 * 
 * array.
 * 
 * 
 * 
 * @author Erin Parker & ??
 * 
 */

public class PriorityQueue<T> {

	private int currentSize;

	private T[] array;

	private Comparator<? super T> cmp;

	/**
	 * 
	 * Constructs an empty priority queue. Orders elements according to their
	 * 
	 * natural ordering (i.e., T is expected to be Comparable) T is not forced to be
	 * 
	 * Comparable.
	 * 
	 */

	@SuppressWarnings("unchecked")

	public PriorityQueue() {

		currentSize = 0;

		cmp = null;

		array = (T[]) new Object[10]; // safe to ignore warning

	}

	/**
	 * 
	 * Construct an empty priority queue with a specified comparator. Orders
	 * 
	 * elements according to the input Comparator (i.e., T need not be Comparable).
	 * 
	 */

	@SuppressWarnings("unchecked")

	public PriorityQueue(Comparator<? super T> c) {

		currentSize = 0;

		cmp = c;

		array = (T[]) new Object[10]; // safe to ignore warning

	}

	/**
	 * 
	 * @return the number of items in this priority queue.
	 * 
	 */

	public int size() {

		return currentSize;

	}

	/**
	 * 
	 * Makes this priority queue empty.
	 * 
	 */

	public void clear() {

		currentSize = 0;

	}

	/**
	 * 
	 * @return the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * 
	 * 
	 *                                (Runs in constant time.)
	 * 
	 */

	public T findMin() throws NoSuchElementException {

		// FILL IN -- do not return null

		if (currentSize == 0) {

			throw new NoSuchElementException();

		}

		return array[0];

	}

	/**
	 * 
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * 
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * 
	 * 
	 *                                (Runs in logarithmic time.)
	 * 
	 */

	public T deleteMin() throws NoSuchElementException {

		// FILL IN -- do not return null

		// if the heap is empty, throw a NoSuchElementException

		if (currentSize == 0) {

			throw new NoSuchElementException();

		}

		// store the minimum item so that it may be returned at the end

		T minimum = array[0];

		// replace the item at minIndex with the last item in the tree

		array[0] = array[currentSize - 1];

		// update size
		currentSize--;

		// percolate the item at minIndex down the tree until heap order is restored

		// It is STRONGLY recommended that you write a percolateDown helper method!

		percolateDown(0);

		// return the minimum item that was stored

		return minimum;

	}

	private void percolateDown(int index) {

		int minChildIndex;

		if (array[getLeftChild(index)] == null && array[getRightChild(index)] == null) {

			return;

		} else if (array[getLeftChild(index)] != null && array[getRightChild(index)] == null) {

			if (compare(array[getLeftChild(index)], array[index]) < 0) {

				swap(index, getLeftChild(index));

			}

			return;

		} else if (array[getLeftChild(index)] == null && array[getRightChild(index)] != null) {

			if (compare(array[getRightChild(index)], array[index]) < 0) {

				swap(index, getRightChild(index));

			}

			return;

		}

		if (compare(array[getLeftChild(index)], array[getRightChild(index)]) > 0)

			minChildIndex = getRightChild(index);

		else

			minChildIndex = getLeftChild(index);

		if (compare(array[minChildIndex], array[index]) < 0) {

			swap(index, minChildIndex);

			percolateDown(minChildIndex);

		}

	}

	/**
	 * 
	 * Adds an item to this priority queue.
	 * 
	 * 
	 * 
	 * (Runs in constant time.)
	 * 
	 * 
	 * 
	 * @param x -- the item to be inserted
	 * 
	 */

	public void add(T x) {

		// FILL IN

		// if the array is full, double its capacity

		if (currentSize == array.length - 1) {

			T[] temp = (T[]) new Object[array.length * 2];

			for (int i = 0; i < array.length; i++) {

				temp[i] = array[i];

			}

			array = temp;

		}

		// add the new item to the next available node in the tree, so that

		// complete tree structure is maintained

		array[currentSize] = x;

		// update size

		currentSize++;

		// percolate the new item up the levels of the tree until heap order is restored

		// It is STRONGLY recommended that you write a percolateUp helper method!

		percolateUp(currentSize - 1);

	}

	private void percolateUp(int index) {

		if (compare(array[getParent(index)], array[index]) > 0) {

			swap(index, getParent(index));

			percolateUp(getParent(index));

		}

	}

	/**
	 * 
	 * Generates a DOT file for visualizing the binary heap.
	 * 
	 */

	public void generateDotFile(String filename) {

		try {

			PrintWriter out = new PrintWriter(filename);

			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for (int i = 0; i < currentSize; i++) {

				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");

				if (((i * 2) + 1) < currentSize)

					out.println("\tnode" + i + ":f0 -> node" + ((i * 2) + 1) + ":f1");

				if (((i * 2) + 2) < currentSize)

					out.println("\tnode" + i + ":f2 -> node" + ((i * 2) + 2) + ":f1");

			}

			out.println("}");

			out.close();

		} catch (IOException e) {

			System.out.println(e);

		}

	}

	/**
	 * 
	 * Internal method for comparing lhs and rhs using Comparator if provided by the
	 * 
	 * user at construction time, or Comparable, if no Comparator was provided.
	 * 
	 */

	private int compare(T lhs, T rhs) {

		if (cmp == null)

			return ((Comparable<? super T>) lhs).compareTo(rhs); // safe to ignore warning

		// We won't test your code on non-Comparable types if we didn't supply a

		// Comparator

		return cmp.compare(lhs, rhs);

	}

	private int getParent(int index) {

		return (index - 1) / 2;

	}

	private int getLeftChild(int index) {

		return (index * 2) + 1;

	}

	private int getRightChild(int index) {

		return (index * 2) + 2;

	}

	private void swap(int item1, int item2) {

		T temp = array[item1];

		array[item1] = array[item2];

		array[item2] = temp;

	}

	// LEAVE IN for grading purposes

	public Object[] toArray() {

		Object[] ret = new Object[currentSize];

		for (int i = 0; i < currentSize; i++)

			ret[i] = array[i];

		return ret;

	}

}