import javax.swing.JOptionPane;

public class QueueCircularArray implements Queue 
{
	 protected Object Q[];				// array used to implement the queue 
	 protected int rear;				// index for the rear of the queue
	 protected int capacity; 			// The actual capacity of the queue array
	 protected int front;
	 protected int size = 0;			//SIZE TO KEEP TRACK OF ISFULL
	 public static final int CAPACITY = 1000;	// default array capacity
	   
	 public QueueCircularArray() {
		   // default constructor: creates queue with default capacity
		   this(CAPACITY);
	 }

	 public QueueCircularArray(int cap) {
		  // this constructor allows you to specify capacity
		  capacity = (cap > 0) ? cap : CAPACITY;
		  Q = new Object[capacity]; 
	 }
	 
	 public void enqueue(Object n)
	 {
		if (isFull()) {
			 JOptionPane.showMessageDialog(null, "Cannot enqueue object; queue is full.");
			 return;
		 }// we call the isFull method when we want to enqueue something, if the queue is full then we get the error message and the object isn't added
		 
		 
		 size++;// since we have added an object to the queue we increment the size
		 Q[rear] = n;//We insert the object into queue position[rear]
         rear = (rear + 1) % capacity;//then we increment the rear to the next position, if the index is greater than the capacity, the modulo is returned, this gives it the circular nature
         
	 }
	 
	 public Object dequeue()
	 {
		 // Can't do anything if it's empty
		 if (isEmpty())
			 return null;//We need isEmpty so that we can loop through and dequeue all of the objects in the queue until there are none left
		 size--;//when we dequeue an object we reduce the size
		 Object toReturn = Q[front];//the object we return is the first object that was queued, the front
         front = (front + 1) % capacity;//since we have stored the front object in the toReturn object we then increment the front, again using modulo
		
		 
		 return toReturn;// the object is returned and printed out after being cast to a string
	 }
	    
	 public boolean isEmpty()  {
	     return(size == 0);
	     //keeping track of the size we can say that the queue is empty when the size is 0
	 }
	 
	 public boolean isFull() {
		 return(size == (capacity));
		 // Queue is full when the size, + (for enqueue - for dequeue) is equal to the capacity 
	 }
	 
	 public Object front()
	 {
		 if (isEmpty()) return null;
		 return Q[0];
	 }
}