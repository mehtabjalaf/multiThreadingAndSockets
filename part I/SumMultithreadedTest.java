

public class SumMultithreadedTest {
    /**
     * Get sum and max of an array.
     *
     *  
     */
   
   public static void main(String[] args) {
        int[] mynums = new int[]{7,3,5,6,8,15, 9,3,10};
		
		// create two instance of SumThread class, give them name "sum thread A", "sum thread B", 
		// give them the aray, and the range.  One will work on the first half of the array, another will work on the second half of the array
		....
		
		// create two instance of MaxThread class, give them name "max thread X", "max thread Y", 
		// give them the aray, and the range.  One will work on the first half of the array, another will work on the second half of the array
		
		....


		// starts/fire the 4 threads so they work in parallel

		....


	    // wait for the 4 threads to finish, and retrieve the results.

		...

		int sum
		int max

	
		
		System.out.println(sum + " " + max  );
		Date d = new Date();
		System.out.println(d); 

		// store the result data to the instancre of SumMaxResult class

		// write the object to a disk file "result.txt"

		...


		System.out.println("Writing success");

		
   }	

}


 