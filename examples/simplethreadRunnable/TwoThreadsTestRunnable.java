public class TwoThreadsTestRunnable {
        
		public static void main (String[] args) {
                
		Thread t = Thread.currentThread(); System.out.println("Current thread: " + t);

		Runnable t1 = new SimpleThreadRunnable("Runnable Thread1");
		SimpleThreadRunnable t2 = new SimpleThreadRunnable("Runnable Thread2");
				
		Thread td = new Thread (t1);
		Thread td2 = new Thread (t2);

	 
		td.start();
                td2.start();

//
		try
		{
			td.join(); System.out.println("== t1 join == ");
			td2.join(); System.out.println("== t2 join  == ");
		}
		catch (InterruptedException e)
		{
			;
		}
//

		System.out.println("== end of main == ");
	}
}
