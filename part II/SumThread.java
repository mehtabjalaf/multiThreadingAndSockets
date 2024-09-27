/**
 * This thread finds the sum of values of a subsection of an given array.
 *  
 */
 

public class SumThread .. {
    private String name;  // name of the thread 
	private int low, hi;   // range of array  from lo to hi, [lo, hi) include low but does not includ hi
    private int[] arr;   // array to be searched
    private int ans = 0;  // store results

    ....

    
    public void run() {
        ....
			 
		System.out.println(this.name + " finish");
    }

 
 }

// create a similar class called MaxThread.java, which finds the max value in a subsection of an given array.
 