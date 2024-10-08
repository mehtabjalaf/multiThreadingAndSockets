// class to store reusults
import java.io.*;
import java.util.Date;
class SumMaxResult implements Serializable  {
	private int arr[]; // the array operated on
    private int max;   // max value
	private int sum;   // sum value
	private Date date;  // result created date/time

    /* this is the class in which we store the object of the max and sum 
     * you can think about this as our persist class */
	
	public SumMaxResult(int[] holder, Date date, int sum, int max) {
		this.arr = holder;
		this.date = date;
		this.max = max;
		this.sum = sum;
	}

	public int[] getArr() {
		return arr;
	}

	public void setArr(int[] arr) {
		this.arr = arr;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
		