/**
 * Nelian E. Colon Collazo
 * 802-09-1384
 * ICOM 4035 Sec. 100
 * 13 de marzo de 2011
 * 
 */

package implementedClasses;

import interfaces.Node;

/**
 * Class DNode, the type of node in this list. 
 * @author Nelian E. Colon Collazo
 *
 * @param <E> data type for the data element of the node
 */
public class DNode<E> implements Node<E> {
	private E element; 
	private DNode<E> prev, next; 

	/**
	 * Default Constructor
	 * All variables have a null value
	 */
	public DNode() {}

	/**
	 * Constructor - Initializes the element to the one received
	 * 
	 * @param e the object to be assigned as the element in the node
	 */
	public DNode(E e) { 
		element = e; 
	}

	/**
	 * Constructor - Initializes the class variables to the ones received.
	 * 
	 * @param e the object to be assigned as the element in the node
	 * @param p reference to the node before current
	 * @param n reference to the node next to current
	 */
	public DNode(E e, DNode<E> p, DNode<E> n) { 
		prev = p; 
		next = n; 
	}

	/**
	 * Returns the node previous to the current one
	 * 
	 * @return the reference to the node previous to the current one
	 */
	public DNode<E> getPrev() {
		return prev;
	}

	/**
	 * Changes the previous node to the current one
	 * 
	 * @param prev the new node to be referenced as the previous node
	 */
	public void setPrev(DNode<E> prev) {
		this.prev = prev;
	}

	/**
	 * Returns the node next to the current one
	 * 
	 * @return the reference to the node next to the current one
	 */
	public DNode<E> getNext() {
		return next;
	}

	/**
	 * Changes the node after the current one
	 * 
	 * @param prev the new node to be referenced as the next node
	 */
	public void setNext(DNode<E> next) {
		this.next = next;
	}
	/**
	 * Returns the element in the current node
	 * 
	 * @return the element referenced in the current node
	 */
	public E getElement() {
		return element; 
	}

	/**
	 * Changes the current element in the node
	 * 
	 * @param the object to be the new element in the node
	 */
	public void setElement(E data) {
		element = data; 
	} 


	/**
	 * Sets the previous and next of the current node to null.
	 */
	public void cleanLinks() { 
		prev = next = null; 
	}

}