import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
   This program demonstrates how to use a socket to communicate
   with a web server. Supply the name of the host and the
   resource on the command-line, for example
   java WebGet horstmann.com index.html
*/
public class WebGet
{
   public static void main(String[] args) throws IOException
   {
      // Get command-line arguments

      String host = "";
      String resource = "/";

      if (args.length == 2)
      {
         host = args[0];
         resource = args[1];
      }
      else if (args.length == 1)
      {
		  host = args[0];
		  //resource = "/";
      }
       
      // Open socket

      final int HTTP_PORT = 80;
      try (Socket s = new Socket(host, HTTP_PORT))
      {
         // Get streams
      
         InputStream instream = s.getInputStream();
         OutputStream outstream = s.getOutputStream();

         // Turn streams into scanners and writers

         Scanner in = new Scanner(instream);
         PrintWriter out = new PrintWriter(outstream);      

         // Send command

         /*String command = "GET " + resource + " HTTP/1.1\r\n"  
            + "Host: " + host + "\r\n\r\n";
         System.out.println(command);*/
		 System.out.println("GET " + resource + " HTTP/1.1");
		 System.out.println("Host: " + host + "\n");

		 out.println("GET " + resource + " HTTP/1.1");
		 out.println("Host: " + host);
		 out.println();

         out.flush();

         // Read server response

         while (in.hasNextLine())
         {
            String input = in.nextLine();
            System.out.println(input);
         }
      }

      // The try-with-resources statement closes the socket
   }
}

