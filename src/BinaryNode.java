//package TreePackage;
class BinaryNode<T> 
{
   private T data;
   private BinaryNode<T> left;
   private BinaryNode<T> right;

   public BinaryNode()
   {
      this(null); // call next constructor
   } // end default constructor

   public BinaryNode(T dataPortion)
   {
      this(dataPortion, null, null); // call next constructor
   } // end constructor

   public BinaryNode(T dataPortion, BinaryNode<T> leftChild,
                                    BinaryNode<T> rightChild)
   {
      data = dataPortion;
      left = leftChild;
      right = rightChild;
   } // end constructor

   public T getData()
   {
      return data;
   } // end getData

   public void setData(T newData)
   {
      data = newData;
   } // end setData

   public BinaryNode<T> getLeftChild()
   {
      return left;
   } // end getLeftChild

   public void setLeftChild(BinaryNode<T> leftChild)
   {
      left = leftChild;
   } // end setLeftChild

   public boolean hasLeftChild()
   {
      return left != null;
   } // end hasLeftChild

   public boolean isLeaf()
   {
      return (left == null) && (right == null);
   } // end isLeaf
   
   public BinaryNode<T> getRightChild()
   {
      return right;
   } // end getLeftChild

   public void setRightChild(BinaryNode<T> rightChild)
   {
      right = rightChild;
   } // end setLeftChild

   public boolean hasRightChild()
   {
      return right != null;
   } // end 

   public int getHeight()
   {
       return getHeight(this); // call private getHeight
   } // end getHeight
   
   private int getHeight(BinaryNode<T> node)
   {
       int height = 0;
       
       if (node != null)
			height = 1 + Math.max(getHeight(node.left), 
				 getHeight(node.right));
       
       return height;
   } // end getHeight
    
   public int getNumberOfNodes()
   {
       int leftNumber = 0;
       int rightNumber = 0;
       
       if (left != null)
			leftNumber = left.getNumberOfNodes();
       
       if (right != null)
			rightNumber = right.getNumberOfNodes();
       
       return 1 + leftNumber + rightNumber;
   } // end getNumberOfNodes
   
   public BinaryNode<T> copy()
   {
       BinaryNode<T> newRoot = new BinaryNode<T>(data);

       if (left != null)
			newRoot.left =  left.copy();
    
       if (right != null)
			newRoot.right = right.copy();
       
       return newRoot;	
   } // end copy
} // end BinaryNode