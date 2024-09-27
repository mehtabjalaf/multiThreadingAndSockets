import java.io.*;
import java.util.*;

class Result implements Serializable  {
	
    private int sum;
	private int diff;
	
    public Result(int s, int d) {
        this.sum = s;
		this.diff = d;
		
		
	}
 

	public int getSum() {
		return sum;
	}
	public int getDiff() {
		return diff;
	}

	
}
		