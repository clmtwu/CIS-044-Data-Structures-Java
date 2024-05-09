public class BinarySearchTreeLab < T extends Comparable < ? super T >> {
    public static void main(String[] args)  {
        BinarySearchTreeLab<Integer> tree = new BinarySearchTreeLab<>(10);
        tree.add(5);
        tree.add(15);
        tree.add(2);
        tree.add(17);

        System.out.println("Is the tree balanced? " + tree.isBalanced());
        System.out.println("Is the tree a valid binary search tree? " + tree.isBST());
        System.out.println("What is the inorder predecessor of 10? " + tree.getPredecessor(10));
    }
    /*
     * Grade: 
     * -10 analysis of running time for countingSort is incorrect. It should be O(m + n).
     */
    
    // P1
    /* Time Complexity = O(n)
     * Memory Complexity = O(h), h = height of tree
     * Tree is balanced if the difference in height of left and right subtrees is no more than one
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(BinaryNode <T> node) {
        if (node == null) {
            return true; // Empty tree is considered balanced (height 0)
        }
        int leftHeight = (node.getLeftChild() != null) ? node.getLeftChild().getHeight() : 0; 
        int rightHeight = (node.getRightChild() != null) ? node.getRightChild().getHeight() : 0;
        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(node.getLeftChild()) && isBalanced(node.getRightChild())) {
        return true;
        }
        return false;
    }
    
    // P2
    // using private method to solve recursively
    public boolean isBST() {
        return isBST(root, getMin(), getMax());
    }

    //finding absolute biggest leaf (located rightmost node of right subtree) using while loop
    private T getMax() {
        BinaryNode<T> current = root;
        while (current.getLeftChild() != null) {
            current = current.getRightChild();
        }
        return (current.getData());
    }

    //finding absolute smallest leaf (located leftmost node of left subtree) using while loop
    private T getMin() {
        BinaryNode<T> current = root;
        while (current.getLeftChild() != null) {
            current = current.getLeftChild();
        }
        return (current.getData());
    }

    private boolean isBST(BinaryNode<T> node, T min, T max) {
        //if targeted node is null, then we have ended one of the subtree transversals with no faults (return true)
        if (node == null) { 
            return true;
        }
        //breaking recursion when leftmost is bigger than node or if rightmost is smaller than node
        if (node.getData().compareTo(findLargest(node).getData()) < 0 || node.getData().compareTo(max) > 0) {
            return false;
        }
        //recursively calling this method for each left child of left subtree and right child of right subtree, respectively
        return (isBST(node.getLeftChild(), min, node.getData()) && isBST(node.getRightChild(), node.getData(), max));
    }

    // P3
    //Time Complexity = O(h) = O(logn), h = height of tree and n = number of total nodes
    public T getPredecessor(T entry) {
        BinaryNode<T> current = root;
        BinaryNode<T> predecessor = null;

        while (current != null) {
            //right subtree
            if (entry.compareTo(current.getData()) > 0) {
                current = current.getRightChild();
            } 
            //left subtree
            else if (entry.compareTo(current.getData()) < 0) {
                predecessor = current;
                current = current.getLeftChild();
            } 
            //getting inorder for the node
            else {
                if (current.getLeftChild() != null) {
                    predecessor = current.getLeftChild();
                    while (predecessor.getRightChild() != null) {
                        predecessor = predecessor.getRightChild();
                    }
                }
                return predecessor.getData();
            }
        }

        // Not found or smallest element
        return current != null && current.getData().compareTo(entry) == 0 ? current.getData() : null;
        
    }

    private BinaryNode<T> root;
    
    public BinarySearchTreeLab () {
	    root = null;
    } 
    
    public BinarySearchTreeLab (T rootData) {
		root = new BinaryNode<T>(rootData);
    } 
    
    public T get(T entry) {
        return getEntry (root, entry);
    } 

    private T getEntry (BinaryNode<T> rootNode, T entry) {
        T result = null;
        if (rootNode != null) {
            T rootEntry = rootNode.getData ();
            if (entry.compareTo(rootEntry) == 0)
                result = rootEntry;
            else if (entry.compareTo(rootEntry) < 0)
                result = getEntry(rootNode.getLeftChild (), entry);
            else
                result = getEntry(rootNode.getRightChild (), entry);
        } 
        return result;
    }

    public boolean contains (T entry) {
        return get(entry) != null;
    }
    
    // Adds newEntry to the nonempty subtree rooted at rootNode.
    private T addEntry (BinaryNode< T > rootNode, T newEntry) {
        // assume that rootNode is NOT null
        T result = null;
        int comparison = newEntry.compareTo (rootNode.getData ());
        if (comparison == 0) { // duplicates NOT allowed
            result = rootNode.getData ();
            rootNode.setData (newEntry);
        }
        else if (comparison < 0) {
            if (rootNode.hasLeftChild ())
                result = addEntry (rootNode.getLeftChild (), newEntry);
            else
                rootNode.setLeftChild (new BinaryNode < T > (newEntry));
        }
        else {
            if (rootNode.hasRightChild ())
                result = addEntry (rootNode.getRightChild (), newEntry);
            else
                rootNode.setRightChild (new BinaryNode < T > (newEntry));
        } // end if
        return result;
    } // end addEntry

    public T add (T newEntry) {
        T result = null;
        if (root == null)
            root = new BinaryNode<T>(newEntry);
        else
            result = addEntry (root, newEntry);
        return result;
    } // end add

    class ReturnObject {
        T data;
        public void set(T newData) { data = newData; }
        public T get() { return data; }
    }

    public T remove (T entry) {
        ReturnObject oldEntry = new ReturnObject();
        BinaryNode<T> newRoot = removeEntry (root, entry, oldEntry);
        root = newRoot;
        return oldEntry.get ();
    } // end remove
    
    // Removes an entry from the tree rooted at a given node.
    // rootNode is a reference to the root of a tree.
    // entry is the object to be removed.
    // oldEntry is an object whose data field is null.
    // Returns the root node of the resulting tree; if entry matches
    // an entry in the tree, oldEntry's data field is the entry
    // that was removed from the tree; otherwise it is null.
	// 
	// Why removeEntry returns BinaryNode<T>
	//    Answer: To return a new modified tree: example root node removed so root of tree will change
    private BinaryNode<T> removeEntry (BinaryNode<T> rootNode, T entry, ReturnObject oldEntry) {
        if (rootNode != null) {
            T rootData = rootNode.getData ();
            int comparison = entry.compareTo (rootData);
            if (comparison == 0) { // entry == root entry
                oldEntry.set (rootData);
                rootNode = removeFromRoot (rootNode);
            }
            else if (comparison < 0) { // entry < root entry
                BinaryNode<T> leftChild = rootNode.getLeftChild ();
                BinaryNode<T> newLeftChild = removeEntry(leftChild, entry, oldEntry);
                rootNode.setLeftChild (newLeftChild);
            }
            else { // entry > root entry
                BinaryNode< T > rightChild = rootNode.getRightChild ();
				BinaryNode<T> newRightChild = removeEntry (rightChild, entry, oldEntry);
				rootNode.setRightChild (newRightChild);
            } 
        } 
        return rootNode;
    } 
    
    // Removes the entry in a given root node of a subtree.
    // rootNode is the root node of the subtree.
    // Returns the root node of the revised subtree.
    private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode) {
        // Case 1: rootNode has two children
        if (rootNode.hasLeftChild () && rootNode.hasRightChild ()) {
            // find node with largest entry in left subtree
            BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild ();
            BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
            // replace entry in root
            rootNode.setData (largestNode.getData ());
            // remove node with largest entry in left subtree
            rootNode.setLeftChild (removeLargest(leftSubtreeRoot));
        } // end if
        // Case 2: rootNode has at most one child
        else if (rootNode.hasRightChild ())
            rootNode = rootNode.getRightChild ();
        else
            rootNode = rootNode.getLeftChild ();
        return rootNode;
    }
    
    // Finds the node containing the largest entry in a given tree.
    // rootNode is the root node of the tree.
    // Returns the node containing the largest entry in the tree.
    private BinaryNode<T> findLargest (BinaryNode<T> rootNode){
        if (rootNode.hasRightChild ())
            rootNode = findLargest (rootNode.getRightChild ());
        return rootNode;
    } 
    
    // Removes the node containing the largest entry in a given tree.
    // rootNode is the root node of the tree.
    // Returns the root node of the revised tree.
    private BinaryNode<T> removeLargest (BinaryNode<T> rootNode) {
        if (rootNode.hasRightChild()) {
            BinaryNode<T> rightChild = rootNode.getRightChild ();
            BinaryNode<T> root = removeLargest (rightChild);
            rootNode.setRightChild (root);
        }
        else
            rootNode = rootNode.getLeftChild ();
        return rootNode;
    } 
}