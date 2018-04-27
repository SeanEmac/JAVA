import javax.swing.JOptionPane;

public class ArrayQueue implements Queue 
{
	 protected Object Q[];			
	 protected int rear = -1;		
	 protected int capacity; 	
	 public static final int CAPACITY = 1000;	
	   
	 public ArrayQueue() {
		   this(CAPACITY);
	 }

	 public ArrayQueue(int cap) {
		  capacity = (cap > 0) ? cap : CAPACITY;
		  Q = new Object[capacity]; 
	 }
	 
	 public void enqueue(Object n)
	 {
		 if (isFull()) {
			 JOptionPane.showMessageDialog(null, "Cannot enqueue object; queue is full.");
			 return;
		 }
		 rear++;
		 Q[rear] = n;
	 }
	 
	 public Object dequeue()
	 {
		 if (isEmpty())
			 return null;
		 
		 Object toReturn = Q[0];
		 int i = 1;
		 while (i <= rear) {
			 Q[i-1] = Q[i];
			 i++;
		 }
		 rear--;
		 return toReturn;
	 }
	 
	 public boolean isEmpty()  {
	     return (rear < 0);
	 }
	 
	 public boolean isFull() {
		 return (rear == capacity-1);
	 }
	 
	 public Object front()
	 {
		 if (isEmpty())
			 return null;
		 
		 return Q[0];
	 }
}