package interfaces;

import exceptionClasses.NodeOutOfBoundsException;

public interface LinkedList<E> {

	/**
	 * Determines the number of nodes currently in the linked list. 
	 * @return integer value (>= 0) corresponding to the number of
	 * nodes actually in the linked list.
	 */
	int length(); 

	/**
	 * 
	 * @param target
	 * @return
	 * @throws NodeOutOfBoundsException
	 */
	Node<E> getNodeBefore(Node<E> target) throws NodeOutOfBoundsException; 

	/**
	 * 
	 * @param target
	 * @return
	 * @throws NodeOutOfBoundsException
	 */
	Node<E> getNodeAfter(Node<E> target) throws NodeOutOfBoundsException; 

	/**
	 *  @return reference to the first node of the linked list
	 *  @throws NodeOutOfBoundsException if the linked list is empty
	 */
	Node<E> getFirstNode() throws NodeOutOfBoundsException; 

	/**
	 * 
	 * @return
	 * @throws NodeOutOfBoundsException
	 */
	Node<E> getLastNode() throws NodeOutOfBoundsException; 

	/**
	 * 
	 * @param nuevo
	 */
	void addFirstNode(Node<E> nuevo); 

	/**
	 * 
	 * @param target
	 * @param nuevo
	 */
	void addNodeAfter(Node<E> target, Node<E> nuevo); 

	/**
	 * 
	 * @param target
	 * @param nuevo
	 */
	void addNodeBefore(Node<E> target, Node<E> nuevo); 

	/**
	 * 
	 * @return
	 * @throws NodeOutOfBoundsException
	 */
	Node<E> removeFirstNode() throws NodeOutOfBoundsException; 

	/**
	 * 
	 * @return
	 * @throws NodeOutOfBoundsException
	 */
	Node<E> removeLastNode() throws NodeOutOfBoundsException; 

	/**
	 * 
	 * @param target
	 */
	void removeNode(Node<E> target); 

	/**
	 * 
	 * @param target
	 * @return
	 * @throws NodeOutOfBoundsException
	 */
	Node<E> removeNodeAfter(Node<E> target) throws NodeOutOfBoundsException; 

	/**
	 * 
	 * @param target
	 * @return
	 * @throws NodeOutOfBoundsException
	 */
	Node<E> removeNodeBefore(Node<E> target) throws NodeOutOfBoundsException; 

	/**
	 * Creates a new node instance of the type of nodes that the linked list
	 * uses. The new node will have all its instance fields initialized to
	 * null. The new node is not linked to the list in any way.
	 * @return reference to the new node instance. 
	 */
	Node<E> createNewNode(); 

}
