import javax.swing.JOptionPane;


public class QueueTest {
	public static void main(String[] args) {
		
		//Test Case 1
		Queue q = new QueueCircularArray(5); 
		JOptionPane.showMessageDialog(null, "About to enqueue words onto queue: \nThe end is nigh!");
		q.enqueue("The");
		q.enqueue("end");
		q.enqueue("is");
		q.enqueue("nigh!");
		
		
		/* Test Case 2
		Queue q = new QueueCircularArray(0); 
		JOptionPane.showMessageDialog(null, "About to enqueue words onto queue: \nThe end is nigh!");
		q.enqueue("The");
		q.enqueue("end");
		q.enqueue("is");
		q.enqueue("nigh!");
		*/
		
		/* Test Case 3
		Queue q = new QueueCircularArray(3); 
		JOptionPane.showMessageDialog(null, "About to enqueue words onto queue: \nThe end is nigh!");
		q.enqueue("The");
		q.enqueue("end");
		q.enqueue("is");
		q.enqueue("nigh!");
		*/
		
		/* Test Case 4
		Queue q = new QueueCircularArray(3); 
		JOptionPane.showMessageDialog(null, "About to enqueue words onto queue: \nThe end is nigh!");
		q.enqueue("The");
		q.enqueue("end");
		q.enqueue("is");	
		q.dequeue();
		q.enqueue("nigh!");
		*/
		
		JOptionPane.showMessageDialog(null, "About to dequeue the words ...");
		while(! q.isEmpty()) {
			String word = (String)q.dequeue(); // Note: have to cast Objects popped to String
			JOptionPane.showMessageDialog(null, "Word dequeued: " + word);
		}
		
		System.exit(0);
	}

}