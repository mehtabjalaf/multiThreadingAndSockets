// this class retrieve the results from the disk file
import java.io.*;  
import java.util.Arrays;

class Depersist {  
 
 public static void main (String args[]) throws FileNotFoundException,  IOException, ClassNotFoundException{  
  
  //  open file "result.txt" and retrieve the SumMaxResult object

  //  and output the 4 attributes of the object
  
	 ObjectInputStream readFile = new ObjectInputStream(new FileInputStream("result.txt"));
	 SumMaxResult printObj = (SumMaxResult) readFile.readObject();
     System.out.println(
             Arrays.toString(printObj.getArr()) + 
             "\nsum: " + printObj.getSum() + 
             "\nmax: " + printObj.getMax() + 
             "\ndate: " + printObj.getDate()
     );
     readFile.close();
	
 
 	} 
}