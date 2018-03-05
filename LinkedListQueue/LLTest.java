import javax.swing.JOptionPane;

public class LLTest 
{
	public static void main(String[] args)
	{
			Queue q = new LLQueue();
			// Put some strings onto the queue
			
			JOptionPane.showMessageDialog(null, "About to enqueue words onto queue: \nThe end is nigh!");
			
			
			q.enqueue("The");
			q.enqueue("end");
			q.enqueue("is");
			q.enqueue("nigh!");
			
			
			
			//Test Case 2
			/*
			q.enqueue("The");
			q.enqueue("end");
			q.dequeue();
			q.enqueue("is");
			q.enqueue("nigh!");
			*/
			
			// Now dequeue them from the queue
			JOptionPane.showMessageDialog(null, "About to dequeue the words ...");
			while(!q.isEmpty()) {
				String word = (String)q.dequeue(); // Note: have to cast Objects popped to String
				JOptionPane.showMessageDialog(null, "Word dequeued: " + word);
			}
			
			System.exit(0);
		}

}