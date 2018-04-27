import javax.swing.JOptionPane;

public class ArrayStack implements Stack
{
   protected int capacity; 		
   protected static final int CAPACITY = 1000;	
   protected Object S[];		
   protected int top = -1;	
   
   public ArrayStack() {
	   this(CAPACITY);
   }

   public ArrayStack(int cap) {
	  capacity = (cap > 0) ? cap : CAPACITY;
	  S = new Object[capacity]; 
   }
   
   public void push(Object element) {
	 if (isFull()) {
	   JOptionPane.showMessageDialog(null, "ERROR: Stack is full.");
	   return;
	 }
	 top++;
	 S[top] = element;
   }

   public Object pop() {
	  Object element;
	  if (isEmpty()) {
	     JOptionPane.showMessageDialog(null, "ERROR: Stack is empty.");
	     return  null;
	  }
	  element = S[top];
	  S[top] = null;
	  top--;
	  return element;
   }

   public Object top() {
	 if (isEmpty()) {
		 JOptionPane.showMessageDialog(null, "ERROR: Stack is empty.");
		 return null;
	 }
	 return S[top];
   }
	   
   public boolean isEmpty() {
		  return (top < 0);
   }

   public boolean isFull() {
		  return (top == capacity-1);
   }

   public int size() { 
		 return (top + 1);
   }
 }