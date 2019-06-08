package com.dub.spring.breadthFirstSearch;


public class SimpleQueue<T> implements Queue<T> {
	
	/** A generic queue implementation */
	private ListNode<T> head;// sentinel
	private ListNode<T> last;
	
	public SimpleQueue() {
		head = new ListNode<T>();
		last = head;
	}

	@Override
	public T pop_front() {
		if (this.isEmpty()) {
			return null;
		} else {
			T obj = head.getNext().getObj();
			if (head.getNext() != last) {
				head.setNext(head.getNext().getNext());
			} else {
				head.setNext(null);
				last = head;
			}
			return obj;
		}
		
	}

	@Override
	public void push_back(T obj) {
		ListNode<T> newNode = new ListNode<T>(obj);
		last.setNext(newNode);
		last = newNode;
		
		
	}

	@Override
	public T front() {
		if (!this.isEmpty()) {
			return head.getNext().getObj();
		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return (last == head);
	}
	
	/** used for debugging only */
	public void display() {
		ListNode<T> ptr = head;
		while (ptr.getNext() != null) {
			ptr = ptr.getNext();
			System.out.println(ptr.getObj());
		} 
		
	} 

}
