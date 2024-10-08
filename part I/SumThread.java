/**
 * This thread finds the sum of values of a subsection of an given array.
 *  
 */
import java.lang.Thread;

 

public class SumThread implements Runnable {
    private String name;  // name of the thread 
	private int low, hi;   // range of array  from lo to hi, [lo, hi) include low but does not includ hi
    private int[] arr;   // array to be searched
    private int ans = 0;  // store results

    // constructor
    public SumThread(int[] holder, String name, int start, int end) {
    	this.arr = holder;
    	this.low = start;
    	this.hi = end;
    	this.name = name;
    }

    
    public void run() {

    	for (int i = this.low; i < this.hi; i++) {
    		ans += this.arr[i];
    	}
    	
		System.out.println(this.name + " finish");
    }
    
    public int getSum() {
    	return this.ans;
    }
   
 }

// create a similar class called MaxThread.java, which finds the max value in a subsection of an given array.
 