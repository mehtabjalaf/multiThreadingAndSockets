import java.io.*; 
import java.net.*; 
class TCPClientV05 { 
    public static void main(String argv[]) throws Exception 
    { 
        String sentence;      
        String modifiedSentence; 

        BufferedReader inFromUser = 
          new BufferedReader(new InputStreamReader(System.in)); 

        //Socket clientSocket = new Socket("localhost", 6789); 
		Socket clientSocket = new Socket(argv[0], Integer.parseInt(argv[1]) );

        PrintWriter outToServer = 
           new PrintWriter(clientSocket.getOutputStream(), true); 

       BufferedReader inFromServer = 
          new BufferedReader(new
          InputStreamReader(clientSocket.getInputStream())); 

        while ( (sentence = inFromUser.readLine()) != null )
        {
			outToServer.println(sentence);
			if (sentence.equals("done"))
			{ 
				break;
			}

			// else

			modifiedSentence = inFromServer.readLine(); 

			System.out.println("FROM SERVER: " + modifiedSentence); 

        }
        clientSocket.close(); 
	 }
 }
