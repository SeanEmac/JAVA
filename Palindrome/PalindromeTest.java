
public class PalindromeTest {
	private static String IN = "";//input string
	
	public void input()
	{
		IN = javax.swing.JOptionPane.showInputDialog("Enter a word to test if it is a Palindrome");//get the input
		IN = IN.replaceAll("[^a-zA-Z0-9]", "");//remove non alphanumeric characters 
		IN = IN.toUpperCase();//change it to upper case
	}
	
	public static String reverse(String input)// warm-up part 1
	{
		Stack stack = new ArrayStack();
		
		for (int i = 0; i < input.length(); i++) {
		    char ch = input.charAt(i);
		    stack.push(ch);
		} 
		String output = "";
		for (int i = 0; i < input.length(); i++) {
			output +=  stack.pop();
		}
		return(output);
	}
	
	public boolean isPalindrome(String input)
	{
		Stack stack = new ArrayStack();// create a Stack
		Queue queue = new ArrayQueue();// create a Queue
		
		for (int i = 0; i < input.length(); i++) {//for each character of the string
		    char ch = input.charAt(i);//get the character i
		    stack.push(ch);//push each character onto the stack
		}
		
		for (int i = 0; i < input.length(); i++) {//for each character of the string
			char ch = input.charAt(i);//get character i
			queue.enqueue(ch);//enqueue the character 
		}
		
		for (int i = 0; i < input.length(); i++) {//this part tests each character to see if they match
			if(stack.pop() != queue.dequeue()) {//if it a Palindrome, the first character popped should equal the first character Dequeued.
				return false;// if this test fails then a false value is returned
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
   	 
	 	PalindromeTest test = new PalindromeTest(); // create a test class
		test.input();// get the input
		
		System.out.println("The input string is: " +IN);
		System.out.println("The Output reverse string is: " +reverse(IN));
		System.out.println();
	    
		if(test.isPalindrome(IN)) {//if this boolean returns true then it is a  Palindrome
			System.out.println("It is a Palindrome");
		}else {//else if it returns false it is not.
			System.out.println("It is not Palindrome");
		}
	}
}
