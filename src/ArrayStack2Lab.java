/**
    A class of stacks whose entries are stored in an array.
    @author Frank M. Carrano
    @version 3.0
*/
import java.util.Arrays;
public class ArrayStack2Lab<T> implements StackInterface<T>
{
    
    /** 
     * @param args
     */
    public static void main(String[] args)  {
		ArrayStack2Lab<String> EMPTY =  new ArrayStack2Lab<String>();
        EMPTY.display();
        ArrayStack2Lab<String> TWOITEMS =  new ArrayStack2Lab<String>();
        TWOITEMS.push("ITEM ONE");
        TWOITEMS.push("ITEM TWO");
        TWOITEMS.display();
        System.out.println("Removing 3 indicies: ");
        System.out.println("Final removed incidies: " + TWOITEMS.remove(3));
        TWOITEMS.display();
        ArrayStack2Lab<Integer> FIVENUMBERS = new ArrayStack2Lab<Integer>();
        FIVENUMBERS.push(1);
        FIVENUMBERS.push(2);
        FIVENUMBERS.push(3);
        FIVENUMBERS.push(4);
        FIVENUMBERS.push(5);
        FIVENUMBERS.push(6);
        System.out.println("Removing 4 indicies: ");
        System.out.println("Final removed incidies: " + FIVENUMBERS.remove(4));
        FIVENUMBERS.display();

	}

    // Problem 1
    public void display() {
        if (isEmpty()) {
            System.out.println("The stack is empty. ");
        } //checking if empty
        else {
            int index = topIndex;
            while (index >= 0) {
                System.out.println(stack[index]);
                index--;
            } //printing stack indexes from the top to bottom using int index
        }

    } //end display()

    // Problem 2
    public int remove(int n) {
        int temp = 0; 
        for (int i = 0; i < n && !isEmpty(); i++) {
            pop();
            temp++;
            //popping and incrementing for each n for each stack index
            //works for n > index since it will iteratively check if it is empty beforehand before running the for-loop
        }
        return temp;
    } //end remove()

    /* Terminal Output:
        The stack is empty.
        ITEM TWO
        ITEM ONE
        Removing 3 indicies:
        Final removed incidies: 2
        The stack is empty.
        Removing 4 indicies:
        Final removed incidies: 4
        2
        1
     */
    
   private T[] stack;    // array of stack entries
   private int topIndex; // index of top entry
   private static final int DEFAULT_INITIAL_CAPACITY = 50;
  
   public ArrayStack2Lab()
   {
      this(DEFAULT_INITIAL_CAPACITY);
   } // end default constructor
  
   public ArrayStack2Lab(int initialCapacity)
   {
      // the cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
      topIndex = -1;
  } // end constructor
  
   public void push(T newEntry)   
   {  
       ensureCapacity();      
       topIndex++;      
       stack[topIndex] = newEntry;   
   } // end push   
   
   private void ensureCapacity()   
   {      if (topIndex == stack.length - 1) // if array is full, double size of array      
	   stack = Arrays.copyOf(stack, 2 * stack.length);   
   } // end ensureCapacity
   
   public T peek()   
   {  
       T top = null;         
       if (!isEmpty())       
	   top = stack[topIndex];             
       return top;   
   } // end peek
   
   public T pop()
   {
       T top = null;
       
       if (!isEmpty()) {
	   top = stack[topIndex];
	   stack[topIndex] = null;
	   topIndex--; 
       } // end if
       
       return top;
   } // end pop
   
   public boolean isEmpty()
   {   
       return topIndex < 0;
   } // end isEmpty

   public void clear()
   {
       for(int i = 0; i <= topIndex; ++i)
	   stack[i] = null;
       topIndex = -1;
   }
   
} // end ArrayStack2Lab
