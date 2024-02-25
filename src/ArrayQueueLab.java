public class ArrayQueueLab<T> implements QueueInterface<T>  {

	public static void main(String[] args)  {
		// Add you lab tests here
	
	}

	// Problem 1, part 1
	public void splice(QueueInterface<T> anotherQueue) {

	}

	// Problem 1, part 2
	public boolean enqueueNoDuplicate(T item) {
		return true;
		//TODO: FIX
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
