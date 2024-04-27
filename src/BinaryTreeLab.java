//package TreePackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack ; // for Stack

public class BinaryTreeLab<T> {
    public static void main(String args[]) {
        //Leaves in tree: 
		BinaryTreeLab <String> d = new BinaryTreeLab <String> ("d");
        BinaryTreeLab <String> e = new BinaryTreeLab <String> ("e");
        BinaryTreeLab <String> g = new BinaryTreeLab <String> ("g");

        //NonLeaves in tree:
        BinaryTreeLab <String> b = new BinaryTreeLab <String> ("b", d, e);
        BinaryTreeLab <String> f = new BinaryTreeLab <String> ("f", null, g);
        BinaryTreeLab <String> c = new BinaryTreeLab <String> ("c", f, null);
        BinaryTreeLab <String> a = new BinaryTreeLab <String> ("a", b, c);

        //Not in tree:
        BinaryTreeLab <String> z = new BinaryTreeLab <String> ("z");
        
        //Iterator Transversal
        Iterator <String> iter = a.getInorderIterator();
        Iterator <String> ator = z.getInorderIterator();
    
        System.out.print("Let's consider a tree with the in-order transversal of: ");

        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.print(a.getRootData());

        System.out.print("\nWith another tree in the in-order transversal of: ");
        while (iter.hasNext()) {
            System.out.print(ator.next() + " ");
        }
        System.out.print(z.getRootData());

        System.out.println("\n\nUsing a recursive (count1) approach: ");
        System.out.print("How many times does the letter \"z\" appear in the tree? " + a.count1("z"));
        System.out.print("\nHow many times does the letter \"b\" appear in the tree? " + a.count1("b"));

        System.out.println("\n\nUsing an iterative (count2) approach: ");
        System.out.print("How many times does the letter \"z\" appear in the a tree? " + a.count2("z"));
        System.out.print("\nHow many times does the letter \"b\" appear in the a tree? " + a.count2("b"));

        System.out.println("\n\nLet us see if these two unequal trees (a and z) are isomorphic! " + (String.valueOf(isIsomorphic(z, a))).toUpperCase());
        System.out.println("Let us see if the same tree is isomorphic! " + (String.valueOf(isIsomorphic(a, a))).toUpperCase());
	}

    // O() analysis of time and memory:
    // A recursive calculation of count, which will depend on the overloaded method
    // Therefore, please see that method for my answer
    public int count1(T anObject) {
        return count1(root, anObject);
    }

    // O() analysis of time and memory:
    /* Let n be the number of objects in the tree and h be the number of tree levels in the tree
     * Since we are traversing through the entire list once, time complexity = O(n)
     * Memory complexity involves height of the tree. if the tree is optimal (complete tree), it would be O(h)
     * In the worst case, which is my answer, it would be O(n). 
     * With this condition, we are assuming a linkedlist and not a tree where h = n.
     */
    private int count1(BinaryNode<T> rootNode, T anObject) {
        if (rootNode == null) { //base case
            return 0;
        }
        int count = 0;
        if (rootNode.getData().equals(anObject)) { //increment
          count++; // Increment count if there's a match
        }
        //recursion for left and right
        count += count1(rootNode.getLeftChild(), anObject);
        count += count1(rootNode.getRightChild(), anObject);
    
        return count;
      }

    // O() analysis of time and memory:
    /* We are mimicing the process of recursion by directly manipulating the Stack
     * Therefore, if we are merely mimicing the stack to represent recursion, then everything would be the same. 
     * Therefore:
     * Memory = O(n)
     * Time = O(n) worst case
     */
    public int count2(T anObject) {
        int count = 0;
        Iterator<T> iterator = getInorderIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(anObject)) {
                count++;
            }
        }
        return count;
    }

    public static <T> boolean isIsomorphic(BinaryTreeLab<T> t1, BinaryTreeLab<T> t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }

        Iterator <T> i1 = t1.getInorderIterator();
        Iterator <T> i2 = t2.getInorderIterator();

        while (i1.hasNext() && i2.hasNext()) {
            T v1 = i1.next();
            T v2 = i2.next();
            if (!v1.equals(v2)) {
                return false;
            }
        }
        //if t1.size != t2.size, then not isomorphic to begin with
        //returns true if same size
        return !i1.hasNext() && !i2.hasNext();
    }

    protected BinaryNode<T> root;
    
    public BinaryTreeLab() {
	     root = null;
    } // end default constructor
    
    public BinaryTreeLab(T rootData) {
	     root = new BinaryNode<T>(rootData);
    } // end constructor

    public BinaryTreeLab(T rootData, BinaryTreeLab<T> leftTree, 
                                 BinaryTreeLab<T> rightTree) {
       privateSetTree(rootData, leftTree, rightTree);
   } // end constructor

    public void setTree(T rootData)
   {
      root = new BinaryNode<T>(rootData);
   } // end setTree
   
    public void setTree(T rootData, BinaryTreeLab<T> leftTree,
		       BinaryTreeLab<T> rightTree)
   {
      privateSetTree(rootData, leftTree, rightTree);
   } // end setTree

    private void privateSetTree(T rootData, BinaryTreeLab<T> leftTree, 
                                            BinaryTreeLab<T> rightTree)
   {
    
      root = new BinaryNode<T>(rootData);

      if (leftTree != null)
	       root.setLeftChild(leftTree.root);
         
      if (rightTree != null)
         root.setRightChild(rightTree.root);
   } 

    public T getRootData () {
        T rootData = null;
        if (root != null)
            rootData = root.getData();
        return rootData;
   }

    public boolean isEmpty () {
       return root == null;
   }

    public void clear (){
       root = null;
   }
    
   // getHeight and getNumberOfNodes call same functions in BinaryNode<T>
    public int getHeight () {
       return root.getHeight ();
   } 

    public int getNumberOfNodes () {
       return root.getNumberOfNodes ();
   }
   
    public void inorderTraversal() {
       Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();
       BinaryNode<T> currentNode = root;
       
       while (!nodeStack.empty() || currentNode != null) {
      	   while(currentNode != null) {
      	       nodeStack.push(currentNode);
      	       currentNode = currentNode.getLeftChild();
      	   }
      	   if(!nodeStack.empty()) {
      	       BinaryNode<T> nextNode = nodeStack.pop();
      	       System.out.println(nextNode.getData());
      	       currentNode = nextNode.getRightChild();
      	   }
       }
   }
   
    public Iterator<T> getPreorderIterator() {
       return new PreorderIterator();
   }
   
    public Iterator<T> getInorderIterator() {
       return new InorderIterator();
   }
   
    private class PreorderIterator implements Iterator<T> {
       private Stack<BinaryNode<T>> nodeStack;
       
       public PreorderIterator() {
	   nodeStack = new Stack<BinaryNode<T>>();
	   if (root != null)
	       nodeStack.push(root);
       } // end default constructor
       
       public boolean hasNext()  {
	   return !nodeStack.isEmpty();
       } // end hasNext
       
       public T next() {
	   BinaryNode<T> nextNode;
	   
	   if (hasNext()) {
	       nextNode = nodeStack.pop();
	       BinaryNode<T> leftChild = nextNode.getLeftChild();
	       BinaryNode<T> rightChild = nextNode.getRightChild();
	       
	       // push into stack in reverse order of recursive calls
	       if (rightChild != null)
		   nodeStack.push(rightChild);
	       
	       if (leftChild != null)
		   nodeStack.push(leftChild);
	   }
	   else {
	       throw new NoSuchElementException();
	   }
	   return nextNode.getData();
       } // end next
       
       public void remove() {
	   throw new UnsupportedOperationException();
       } // end remove
   } // end PreorderIterator
    
    private class InorderIterator implements Iterator < T >
   {
       private Stack< BinaryNode < T >> nodeStack;
       private BinaryNode< T > currentNode;
       public InorderIterator () {
	   nodeStack = new Stack < BinaryNode< T >> ();
	   currentNode = root;
       } // end default constructor


       public boolean hasNext () {
	   return !nodeStack.isEmpty () || (currentNode != null);
       } // end hasNext


       public T next ()
       {
	   BinaryNode< T > nextNode = null;
	   // find leftmost node with no left child
	   while (currentNode != null) {
	       nodeStack.push (currentNode);
	       currentNode = currentNode.getLeftChild ();
	   } // end while
	   // get leftmost node, then move to its right subtree
	   if (!nodeStack.isEmpty ()) {
	       nextNode = nodeStack.pop ();
	       currentNode = nextNode.getRightChild ();
	   }
	   else
	       throw new NoSuchElementException ();
	   return nextNode.getData ();
       } // end next
       
       
       public void remove () {
	   throw new UnsupportedOperationException ();
       } // end remove

   } // end InorderIterator
} // end BinaryTreeLab