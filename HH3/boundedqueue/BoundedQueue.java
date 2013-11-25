
/**
 * A bounded queue implementation for the KCPB Fellow Application
 * @author Daniel Santiago
 */
public class BoundedQueue<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;
	private int maxSize;
	
	
	/**
	 * Queue constructor
	 * @param maxSize - Maximum sixe of the queue
	 */
	public BoundedQueue(int maxSize) {
		if(maxSize <= 0) {
			throw new RuntimeException("Invalid maxSize " + maxSize + "," +
					" value must be greater than 0");
		}
		
		this.maxSize = maxSize;
	}

	/**
	 * @return true is the queue contains no elements
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * @return the number of elements in the queue
	 */
	public int size() {
		return size;
	}
	
	/**
	 * @return true if the queue is full
	 */
	public boolean isFull() {
		return size == maxSize;
	}
	
	/**
	 * @return the maximum size of the queue
	 */
	public int getMaxSize() {
		return maxSize;
	}
	
	/**
	 * Inserts an element into the back of the queue
	 * @param e element to insert into queue, throws Runtime Exception if queue is full
	 */
	public void enqueue(E e) {
		if(size == maxSize) {
			throw new RuntimeException("Queue is full, size = " + size);
		}
		
		if(size == 0) {
			head = tail = new Node<E>(e);
		} else {
			Node<E> newNode = new Node<E>(e);
			tail.setNext(newNode);
			tail = newNode;
		}
		
		size++;
	}
	
	/**
	 * Removes the element in front of the queue, throws Runtime Exception if queue is empty
	 * @return the element removed from the front of the queue
	 */
	public E dequeue() {
		if (size == 0) {
			throw new RuntimeException("Queue is empty");
		}
		
		E element = head.getElement();
		head = head.getNext();
		size--;
		
		return element;
	}
	
	/**
	 * Gets the element currently in front of the queue, throws Runtime Exception if queue is empty.
	 * @return the element in front of the queue
	 */
	public E peek() {
		if (size == 0) {
		 throw new RuntimeException("Queue is empty");
		}
		
		return head.element;
	}
	
	public String toString() {
		Node<E> curr = head;
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < size; i++) {
			sb.append(curr.getElement());
			if(i < size - 1) {
				sb.append(",");
			}
			curr = curr.getNext();
		}
		sb.append("]");
		return sb.toString();
	}
	
	private class Node<E> {
		private E element;
		private Node<E> next;
		
		public Node(E element) {
			super();
			this.element = element;
		}
		
		public E getElement() {
			return element;
		}
		
		public void setElement(E element) {
			this.element = element;
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public void setNext(Node<E> next) {
			this.next = next;
		}
	}
}
