import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static void main(String args[]) throws Exception {

//	  ServerSocket welcomeSocket = new ServerSocket(..); 
//  
//      while(true) {   // keep on running
//  
//      
//	   }

		Socket socket = null;
		// same vars from client

		// new
		ServerSocket serverSocket = null;
		int port = 4413;
		serverSocket = new ServerSocket(port);
        System.out.println("listening... at port " + port);
		ExecutorService executor = Executors.newCachedThreadPool();

		while (true) {
			socket = serverSocket.accept(); /* creates socket object when connection is made */
			executor.submit(new MultithreadOperation(socket));
		}

	}
}

class MultithreadOperation implements Runnable {

	InputStreamReader inputStreamReader = null;
	OutputStreamWriter outputStreamWriter = null;
	BufferedReader bufferedReader = null;
	BufferedWriter bufferedWriter = null;
	private Socket socket = null;

	public MultithreadOperation(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {

			inputStreamReader = new InputStreamReader(socket.getInputStream());
			outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			// buffers below
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			PrintWriter printer = new PrintWriter(socket.getOutputStream(), true);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

			// this while loop before is what is going to be used to send data back and
			// forth

			// I need to use the arrayList object to interact with the str arrays that I am
			// going to be inputting
			String clientInput;
			List<Integer> holder = new ArrayList<>(); // temp holder for the new str array

			while ((clientInput = bufferedReader.readLine()) != null) {
//				String msgFromClient = bufferedReader.readLine();
//				System.out.println("Client: " + msgFromClient);
//				bufferedWriter.write("MSG received");
//				bufferedWriter.newLine();
//				bufferedWriter.flush(); // manual flush for buffer

				if (clientInput.startsWith("array")) {
					holder.clear();
					String[] newArr = clientInput.split(" ");
					for (int i = 1; i < newArr.length; i++) {
						holder.add(Integer.parseInt(newArr[i]));
					}

					int[] mynums = holder.stream().mapToInt(i -> i).toArray();
					int middleOfArray = mynums.length / 2;

					// now that i organized the array and got the new int[], and middle point, I can
					// use the same code from SumMultithreadedTest
					SumThread sumThreadA = new SumThread(mynums, "sum thread A", 0, middleOfArray);
					SumThread sumThreadB = new SumThread(mynums, "sum thread B", middleOfArray, mynums.length);

					Thread std = new Thread(sumThreadA);
					Thread std2 = new Thread(sumThreadB);

					// create two instance of MaxThread class, give them name "max thread X", "max
					// thread Y",
					// give them the aray, and the range. One will work on the first half of the
					// array, another will work on the second half of the array

					MaxThread maxThreadX = new MaxThread(mynums, "max thread X", 0, middleOfArray);
					MaxThread maxThreadY = new MaxThread(mynums, "max thread Y", middleOfArray, mynums.length);

					Thread mtd = new Thread(maxThreadX);
					Thread mtd2 = new Thread(maxThreadY);

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

					System.out.println("sum:" + sum + " max:" + max);
					Date d = new Date();
					System.out.println(d);

					// store the result data to the instancre of SumMaxResult class

					SumMaxResult persist = new SumMaxResult(mynums, d, sum, max);
					objectOutputStream.writeObject(persist);

				} else { /*
							 * basically if the line didn't start with "array", just go ahead and uppercase
							 * everything
							 */
					printer.println(clientInput.toUpperCase());
				}
			}

			// we have to close the sockets at the end

//			inputStreamReader.close();
//			outputStreamWriter.close();
//			bufferedReader.close();
//			bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
