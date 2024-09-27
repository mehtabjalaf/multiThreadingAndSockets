import java.io.*; 
import java.net.*; 

public class TCPServerV2 { 

  public static void main(String argv[]) throws Exception     { 

      ServerSocket welcomeSocket = new ServerSocket(6789); 
  
      while(true) {   // keep on running
  
       System.out.println("litening ... ");
	   Socket connectionSocket = welcomeSocket.accept(); 

        ClientHandler handler = new ClientHandler (connectionSocket);
        handler.start();
   }
 }

}


 

class ClientHandler extends Thread { 

     Socket clientSocket; 
     String clientSentence; 
     String replySentence; 
     public ClientHandler (Socket s){
        this.clientSocket = s;
     }

  
     public void run(){ 
      
		try{
		//while(true) {   // keep on running   
  
         BufferedReader inFromClient = new BufferedReader(new
                   InputStreamReader(clientSocket.getInputStream()));

         PrintWriter outToClient =  
                    new PrintWriter(clientSocket.getOutputStream()); 

         while (!(clientSentence = inFromClient.readLine()).equals("done")){ 
          
              replySentence =  clientSentence.toUpperCase();
              outToClient.println("Hi thread " + replySentence);
              outToClient.flush();  // or set true
          }
		 
      }catch (IOException e) {;}
  }
 
}