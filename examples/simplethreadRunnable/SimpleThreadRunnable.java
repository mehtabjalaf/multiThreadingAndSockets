import java.lang.Thread;

public class SimpleThreadRunnable implements Runnable {

	  
	String name;
	public SimpleThreadRunnable(String str) 
	{ this.name = str;  }  

	public void run() {
		for (int i = 0; i < 10; i++) {
 			System.out.println( " " + this.name + " -->> "+i);
			try {
				Thread.sleep((long)(Math.random() * 1000));
			} 
			catch (InterruptedException e) {}
		}

		System.out.println("DONE Runnable! " + this.name);
	}
}