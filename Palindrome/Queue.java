
public interface Queue {
	
	public void   enqueue(Object n);
	public Object dequeue();

	public boolean isEmpty();
	public boolean isFull();
	public Object  front();
}
