import java.net.Socket;
import java.io.*;
import java.util.Scanner;


public class Client {
	
	public static void main (String[] args) throws ClassNotFoundException {
		
		
		Socket socket = null;
		/*
		 *  input and output streams are used to talk between clients
		 *  stream is a sequence of data
		 *  byte and character streams
		 *  an input stream reads data from a source 
		 *  	- meaning we need to have an input stream
		 * */
		
		InputStreamReader inputStreamReader = null; // reads from source, bridge from byte to char
		
		/* we also need to send msgs to the server */
		
		OutputStreamWriter outputStreamWriter = null;
		
		/* buffers are used so streams can write large blocks at a time instead of char/byte by char/byte */
		
		BufferedReader bufferedReader = null; 
		
		BufferedWriter bufferedWriter = null;
		
		try {
			socket = new Socket("localhost", 4413);
			
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			PrintWriter printer = new PrintWriter(socket.getOutputStream(), true);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            
            String inputHolder;
			
			/* taking input from console so we need scanner */
			
			Scanner scanner = new Scanner(System.in);
			
			while (true) { /* will always be ruinnning since true */

//				String msgToSend = scanner.nextLine();
//				bufferedWriter.write(msgToSend);
//				bufferedWriter.newLine();
//				bufferedWriter.flush(); /* clears the buffer, flush when enter key is pressed */
//				
//				System.out.println("FROM SERVER: " + bufferedReader.readLine() /* waiting for server to send a response */);
				
				inputHolder = scanner.nextLine();
                printer.println(inputHolder);  // Send the input to the server

                if (inputHolder.startsWith("array")) {
                    SumMaxResult result = (SumMaxResult) objectInputStream.readObject();
                    System.out.println("FROM SERVER: calculating ....... \n" + java.util.Arrays.toString(result.getArr()));
                    System.out.println("Sum: " + result.getSum() + "\nMax: " + result.getMax());
                    System.out.println(result.getDate());
                } else {
                    System.out.println("FROM SERVER: " + bufferedReader.readLine());
                }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}		
}