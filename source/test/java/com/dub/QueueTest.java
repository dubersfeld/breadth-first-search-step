package com.dub;

import com.dub.site.breadthFirstSearch.SimpleQueue;

public class QueueTest {

	public static void main(String[] args) {
		
		System.out.println("main begin");
		
		SimpleQueue<String> queue = new SimpleQueue<>();
		
		queue.display();
		
		//ListNode<String> n1 = new ListNode<>("A");
		
		queue.push_back("A");
		queue.push_back("B");
		queue.push_back("C");
		
		queue.display();
		
		System.out.println("front: " + queue.front());
		
		System.out.println("pop_front: " + queue.pop_front());
		
		queue.display();
		
		System.out.println("pop_front: " + queue.pop_front());
		
		System.out.println("isEmpty: " + queue.isEmpty());
		
		System.out.println("pop_front: " + queue.pop_front());
		
		System.out.println("isEmpty: " + queue.isEmpty());
		
		System.out.println("pop_front: " + queue.pop_front());
		
	
		
		queue.push_back("sator");
		queue.push_back("arepo");
		queue.push_back("tenet");
		
		System.out.println("pop_front: " + queue.pop_front());
		
		queue.push_back("opera");
		
		queue.display();
		
		System.out.println("main completed");
	}

}
