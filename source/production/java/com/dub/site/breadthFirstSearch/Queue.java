package com.dub.site.breadthFirstSearch;


public interface Queue<T> {
	
	/** A generic queue interface */
	
	public T pop_front();
	
	public void push_back(T obj);
	
	public T front();
	
	public boolean isEmpty();
	
}
