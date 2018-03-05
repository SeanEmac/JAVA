public class Timer  {
		
	private static int N = 0;
	
	public void input()
	{
		N = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Enter N"));
	}
	
	public static long LLQueue()
	{
		LLQueue llQueue = new LLQueue();
		long LLEnqueueStart = System.nanoTime();
		
		for(int i=0; i<N; i++){
			llQueue.enqueue("Data" +i);
			
		}
		
		long LLEnqueueEnd = System.nanoTime();
		long LLDequeueStart = System.nanoTime();
		
		for(int i=0; i< N; i++){
			llQueue.dequeue();	
		}
		
		long LLDequeueEnd = System.nanoTime();
		long LLEnqueueTotal = (LLEnqueueEnd - LLEnqueueStart);
		long LLDequeueTotal = (LLDequeueEnd - LLDequeueStart); 

		return(LLDequeueTotal + LLEnqueueTotal);
	}
		  
	public static long ArrayQueue()
	{
		ArrayQueue ArrayQueue = new ArrayQueue(100001);
		long ArrQEnqueueStart =  System.nanoTime();
		
		for(int i=0; i< N; i++){
			ArrayQueue.enqueue("Data" +i);	
		}
		
		long ArrQEnqueueEnd = System.nanoTime();
		long ArrQDequeueStart = System.nanoTime();
		
		for(int i=0; i< N; i++){
			ArrayQueue.dequeue();
		}
		
		long ArrQDequeueEnd = System.nanoTime();
		long ArrQEnqueueTotal = (ArrQEnqueueEnd - ArrQEnqueueStart);
		long ArrQDequeueTotal = (ArrQDequeueEnd - ArrQDequeueStart);
	
		return(ArrQDequeueTotal + ArrQEnqueueTotal);
	}

		
     public static void main(String[] args) {
    	 
    	 	Timer test = new Timer(); 
			test.input();
			long ArrQTime = ArrayQueue();
			long LLTime = LLQueue();
			
			System.out.println("Time taken for N of: " +N);
			System.out.println("LLQueue: " + LLTime);
			System.out.println("ArrayQueue: " +ArrQTime);
	}
}