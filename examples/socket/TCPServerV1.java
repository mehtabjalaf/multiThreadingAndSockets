import java.io.*; 
import java.net.*; 

class TCPServerV1 { 

  public static void main(String argv[]) throws Exception     { 
      String clientSentence; 
      String replySentence; 

      ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(argv[0]) ); //6789); 
  
      while(true) { 
  
        System.out.println("listening ...");
		Socket connectionSocket = welcomeSocket.accept(); 

        BufferedReader inFromClient = new BufferedReader(new
                   InputStreamReader(connectionSocket.getInputStream()));

         PrintWriter outToClient =  
                    new PrintWriter(connectionSocket.getOutputStream(), true); 

         while ( !(clientSentence = inFromClient.readLine()).equals("done")){ 
          
              replySentence =  clientSentence.toUpperCase();
              outToClient.println("Hi: " + replySentence);
          }

	  }
    }
  }
