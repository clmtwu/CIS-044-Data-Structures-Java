import java.util.ArrayList;

public class ArrayQueueLab<T> implements QueueInterface<T>  {
	public static void main(String[] args)  {
		//object initialization
		ArrayQueueLab <Integer> List1 = new ArrayQueueLab<Integer>();
		ArrayQueueLab <Integer> List2 = new ArrayQueueLab<Integer>();
		ArrayQueueLab <String> DuplicateQueue = new ArrayQueueLab<String>();
		ArrayQueueLab <String> NonDuplicateQueue = new ArrayQueueLab<String>();

		//test case initialization
		for (int i = 0; i < 5; i++) {
			List1.enqueue(i);
		}

		for (int i = 5; i < 10; i++) {
			List2.enqueue(i);
		}

		DuplicateQueue.enqueue("Duplicate");
		DuplicateQueue.enqueue("Nonduplicate");

		NonDuplicateQueue.enqueue("I am so unique!");
		NonDuplicateQueue.enqueue("And so am I!");
		
		//case 1:
		System.out.println("Case One: \"ListOne\" Queue containing integers zero through four in order of least to greatest");
		System.out.println("\"ListTwo\" Queue containing integers five through nine in order of least to greatest");
		System.out.println("Splicing \"ListOne\" with \"ListTwo\" in progress...");
		List1.splice(List2);
		System.out.print("Printing out all the elements of List 1 in order: ");
		while(!List1.isEmpty()) {
			System.out.print(List1.dequeue() + " ");
		}
		System.out.println("\nIs List 2 empty as expected? " + (String.valueOf(List2.isEmpty())).toUpperCase());

		//case 2:
		System.out.println("\nCase Two: \"DuplicateQueue\" containing the terms \"Duplicate\" and \"Nonduplicate\"");
		System.out.println("Determining if the item \"Duplicate\" is in the \"DuplicateQueue\": " + (String.valueOf(DuplicateQueue.enqueueNoDuplicate("Duplicate"))).toUpperCase());

		//case 3:
		System.out.println("\nCase Three: \"NonDuplicateQueue\" containing \"I am so unique!\" and \"And so am I!\"");
		System.out.println("Determining if the item \"I believe my uniqueness speaks for itself without the need to express it verbally.\" is in the \"NonDuplicateQueue\": " + (String.valueOf(NonDuplicateQueue.enqueueNoDuplicate("I believe my uniqueness speaks for itself without the need to express it verbally."))).toUpperCase());
		System.out.print("Printing out all the elements of \"NonDuplicateQueue\" in order: ");
		while(!NonDuplicateQueue.isEmpty()) {
			System.out.print(NonDuplicateQueue.dequeue() + " ");
		}
	}
	/* Grade:
	 * -10 enqueueNoDuplicate ArrayList<T> Temporary = new ArrayList <T>(); 
	 * -- no need to create an additional Temporary queue. 
	 * This results in additional memory usage (O(n)) and additional time to process the elements. 
	 * Instead, iterate over all the elements starting at frontIndex and going to backIndex. 
	 * See clear() for an example.
	 */

	/* Output:
	 * 	Case One: "ListOne" Queue containing integers zero through four in order of least to greatest
		"ListTwo" Queue containing int five through nine in order of least to greatest
		Splicing "ListOne" with "ListTwo" in progress...
		Printing out all the elements of List 1 in order: 0 1 2 3 4 5 6 7 8 9
		Is List 2 empty as expected? TRUE

		Case Two: "DuplicateQueue" containing the terms "Duplicate" and "Nonduplicate"
		Determining if the item "Duplicate" is in the "DuplicateQueue": TRUE

		Case Three: "NonDuplicateQueue" containing "I am so unique!" and "And so am I!"
		Determining if the item "I believe my uniqueness speaks for itself without the need to express it verbally." is in the "NonDuplicateQueue": FALSE
		Printing out all the elements of "NonDuplicateQueue" in order: I am so unique! And so am I! I believe my uniqueness speaks for itself without the need to express it verbally.
	 */

	// Problem 1, part 1
	public void splice(QueueInterface<T> anotherQueue) {
		while (!anotherQueue.isEmpty()) { //only continuing if queue is not empty, updating every "cycle"
			//queues up the same queue's dequeue item, effectively splicing it. 
			enqueue(anotherQueue.dequeue()); 
		}	
	}

	// Problem 1, part 2
	public boolean enqueueNoDuplicate(T Target) {
		boolean flag = false;			//flag to check if there is a duplicate. must be initialized to false so that the second part of this assignment holds
        ArrayQueueLab <T> Queue = this;	// since we are only passing in an item, we need to create a new array to hold this same type.
		ArrayList<T> Temporary = new ArrayList <T>(); //making an ArrayList to store temporary values. We cannot iterate through generic type T, so we are making a temporary holding cell for the values we are going to dequeue

        while (!Queue.isEmpty()){ //iterate over all items in the current queue's copy
            T LastExistingItem = Queue.dequeue(); //dequeuing item from the Queue object created above and storing it as a singular value (Target)
            if (LastExistingItem.equals(Target)) {  //If the paramater is the same as our singular value, we can set the flag to true and break
                flag = true;			//since this is a while loop, we are iterating through every target and seeting if any are true
                break;
            }
			Temporary.add(LastExistingItem); //storing the value we just dequeued into an arraylist. not optimal coding but it works
        }

		for (T OriginalElement : Temporary) { //repopulating the original queue
			Queue.enqueue(OriginalElement);
		}

		//If item is already present, no change to the queue takes place and the method returns false
		if (!flag) { //this method only fails if flag has been false, which means the target item has been compared to every node
			this.enqueue(Target); //adding item as an object through this keyboard, since adding through Queue would be a static reference
			//NOTE: BOOLEAN IS NOT CHANGED. flag has been false for if statement to be true and MUST be false as per lab instructions
		}
        
        return flag;
	}

    private T[] queue; // circular array of queue entries and one unused location
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_INITIAL_CAPACITY = 50;
    
    public ArrayQueueLab() {
	this(DEFAULT_INITIAL_CAPACITY);
    } // end default constructor
    
    public ArrayQueueLab(int initialCapacity) {
	// the cast is safe because the new array contains null entries
	@SuppressWarnings("unchecked")
	    T[] tempQueue = (T[]) new Object[initialCapacity + 1];
	queue = tempQueue;
	frontIndex = 0;
	backIndex = initialCapacity;
    } // end constructor
    
    public void enqueue(T newEntry) {
	ensureCapacity();   
	backIndex = (backIndex + 1) % queue.length;
	queue[backIndex] = newEntry;
    } // end enqueue
    
    public T getFront(){
	T front = null;
	if (!isEmpty())
	    front = queue[frontIndex];
	
	return front;
    } // end getFront
    
    public T dequeue() {
	T front = null;
	if (!isEmpty())  {
	    front = queue[frontIndex];
	    queue[frontIndex] = null;
	    frontIndex = (frontIndex + 1) % queue.length;
	} // end if
	
	return front;
    } // end dequeue
    
    private void ensureCapacity() {
	if (frontIndex == ((backIndex + 2) % queue.length))  { // if array is full,
	    T[] oldQueue = queue;
	    int oldSize = oldQueue.length;
	    @SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[2 * oldSize];
	    queue = tempQueue;
	    for (int index = 0; index < oldSize - 1; index++) {
		queue[index] = oldQueue[frontIndex];
		frontIndex = (frontIndex + 1) % oldSize;
	    } // end for
	    
	    frontIndex = 0;
	    backIndex = oldSize - 2;
	} // end if
    } // end ensureCapacity

    public boolean isEmpty() {
	return frontIndex == ((backIndex + 1) % queue.length);
    } // end isEmpty
    
    public void clear() {
	if(!isEmpty()) {
	    for (int index = frontIndex; index != backIndex; index = (index+1)%queue.length)
		queue[index] = null;
	    queue[backIndex] = null;
	}
	frontIndex = 0;
	backIndex = queue.length - 1;
    }
    
} // end ArrayQueueLab
