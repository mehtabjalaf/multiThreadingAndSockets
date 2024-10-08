import java.lang.Thread;
import java.util.Date;
import java.io.*;

public class SumMultithreadedTest {
    /**
     * Get sum and max of an array.
     *
     *  
     */
   
   public static void main (String[] args) {
	   
        int[] mynums = new int[]{7,3,5,6,8,15,9,3,10}; //0-8 , 9 total
		
		// create two instance of SumThread class, give them name "sum thread A", "sum thread B", 
		// give them the aray, and the range.  One will work on the first half of the array, another will work on the second half of the array
        
        int middleOfArray = mynums.length / 2;
        
        SumThread sumThreadA = new SumThread(mynums, "sum thread A", 0, middleOfArray);
        SumThread sumThreadB = new SumThread(mynums, "sum thread B", middleOfArray, mynums.length);
        
        Thread std = new Thread (sumThreadA);
		Thread std2 = new Thread (sumThreadB);
		
		// create two instance of MaxThread class, give them name "max thread X", "max thread Y", 
		// give them the aray, and the range.  One will work on the first half of the array, another will work on the second half of the array
		
		MaxThread maxThreadX = new MaxThread(mynums, "max thread X", 0, 4);
		MaxThread maxThreadY = new MaxThread(mynums, "max thread Y", 5, 8);

		Thread mtd = new Thread (maxThreadX);
		Thread mtd2 = new Thread (maxThreadY);


		// starts/fire the 4 threads so they work in parallel

		std.start();
		std2.start();
		mtd.start();
		mtd2.start();

	    // wait for the 4 threads to finish, and retrieve the results.
		try {
			std.join();
			std2.join();
			mtd.join();
			mtd2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int sum;
		int max;
		
		sum = sumThreadA.getSum() + sumThreadB.getSum();
		
		if (maxThreadX.getMax() > maxThreadY.getMax()) {
			max = maxThreadX.getMax();
		} else {
			max = maxThreadY.getMax();
		}
	
		
		System.out.println("sum:"+ sum + " max:" + max);
		Date d = new Date();
		System.out.println(d); 

		// store the result data to the instancre of SumMaxResult class
		
		SumMaxResult persist = new SumMaxResult(mynums, d, sum, max);

		// write the object to a disk file "result.txt"

		/* add code here */
		
		try {
			
			FileOutputStream newFile = new FileOutputStream("result.txt");
			ObjectOutputStream newOutput = new ObjectOutputStream(newFile);
			newOutput.writeObject(persist);
			newOutput.flush();
			newOutput.close();
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		

		System.out.println("Writing success");

		
   }	

}


 