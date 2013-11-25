

public class BoundedQueueTester {

	public static void main(String[] args) {
		System.out.println("Tester class for BoundedQueue\n");

		BoundedQueue<Integer> bq = new BoundedQueue<>(10);
		
		maxSizeOfQ(bq);
		
		insert(bq, 5);
		insert(bq, 2);
		insert(bq, 8);
		insert(bq, 10);
		insert(bq, -8);
		insert(bq, 300);
		insert(bq, 101);
		insert(bq, 0);
		insert(bq, 1024);
		insert(bq, -100);
		insert(bq, 20); //11th Element insert throws exception
		
		remove(bq);
		remove(bq);
		remove(bq);
		remove(bq);
		remove(bq);
		remove(bq);
		remove(bq);
		remove(bq);
		remove(bq);
		remove(bq);
		remove(bq); //11th Remove throws exception
		
		peek(bq); //Peek on empty queue throws exception
		
		insert(bq, 25);
		sizeOfQ(bq);
		peek(bq);
		insert(bq, 18);
		peek(bq);
		insert(bq, 12);
		sizeOfQ(bq);
		remove(bq);
		peek(bq);
		insert(bq, -15);
		peek(bq);
		insert(bq, 99);
		remove(bq);
		remove(bq);
		remove(bq);
		remove(bq);
		sizeOfQ(bq);
		
		//Construction of invalid bq
		try {
			System.out.println("Contructing queue with -2 as maxSize...");
			bq = new BoundedQueue<Integer>(-2);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println();	
		}
		
		System.out.println("Test Done");
	}

	private static void peek(BoundedQueue<Integer> bq) {
		System.out.print("Peeking into queue...");
		try {
			System.out.println(" Q returned: " + bq.peek());
			printQ(bq);
		} catch (RuntimeException e) {
			System.out.println();
			System.out.println(e);
			System.out.println();
		}
	}

	private static void insert(BoundedQueue<Integer> bq, int i) {
		System.out.print("Adding " + i + " to queue...");
		try {
			bq.enqueue(i);
			printQ(bq);
		} catch (RuntimeException e) {
			System.out.println();
			System.out.println(e);
			System.out.println();
		}
		
	}
	
	private static void remove(BoundedQueue<Integer> bq) {
		System.out.print("Removing element from queue...");
		try {
			System.out.println(" Q returned: " + bq.dequeue());
			printQ(bq);
		} catch (RuntimeException e) {
			System.out.println();
			System.out.println(e);
			System.out.println();
		}
	}
	
	private static void printQ(BoundedQueue<Integer> bq) {
		System.out.println("-- BQ now contains:");
		System.out.println(bq.toString());
		System.out.println("--");
		System.out.println();
	}
	
	private static void sizeOfQ(BoundedQueue<Integer> bq) {
		System.out.println("Size of BQ is " + bq.size());
		System.out.println();
	}
	
	private static void maxSizeOfQ(BoundedQueue<Integer> bq) {
		System.out.println("Size of BQ is " + bq.size());
		System.out.println();
	}

}
