public class LLQueue implements Queue {
	
	SLinkedList newSLinkedList;
	
	 public LLQueue() {
		newSLinkedList = new SLinkedList();
	 }
	 
    public void enqueue(Object n) {
    	if(isEmpty()) {
    		newSLinkedList.insertHead(n);//if the Queue is empty then we just insert in the head
    	}else {
    		
    	newSLinkedList.gotoTail();//if its not empty, then we got to the tail
    	newSLinkedList.insertNext(n);//and insert at the next node
    	}
    }

    public Object dequeue() {
    	if(isEmpty()) {
    		return null;//can't dequeue any more 
    	}else {

    	newSLinkedList.gotoHead();//go to the head
    	Object toReturn = newSLinkedList.getCurr();//copy the head in toReturn
    	newSLinkedList.deleteHead();// delete the head
    	
    	return toReturn;//return the old head
    	}
    	
    }

    public boolean isEmpty(){
       return(newSLinkedList.isEmpty());//just call the already made isEmpty()
    }
    public boolean isFull(){
    	return false;
    }
    
    public Object  front() {
    	return null;
    }
    

}