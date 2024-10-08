import java.lang.Thread;

public class MaxThread implements Runnable {
	
	
    private String name;  // name of the thread 
	private int low, hi;   // range of array  from lo to hi, [lo, hi) include low but does not includ hi
    private int[] arr;   // array to be searched
    private int ans = 0;  // store results
    private int currentMax = 0;

    // constructor
    public MaxThread(int[] holder, String name, int start, int end) {
    	this.arr = holder;
    	this.low = start;
    	this.hi = end;
    	this.name = name;
    }

    
    public void run() {

    	for (int i = this.low; i < this.hi; i++) {
    		this.ans = this.arr[i];
    		if (this.ans > this.currentMax) {
    			this.currentMax = this.ans;
    		}
    	}
    	
		System.out.println(this.name + " finish");
    }
    
    public int getMax() {
    	return this.currentMax;
    }
	
	
}