import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

import java.io.FileReader;


// Each Client Connection will be managed in a dedicated Thread
public class JavaHTTPServer implements Runnable{ 
	
	static final File WEB_ROOT = new File(".");
	static final String DEFAULT_FILE = "index.html";
	static final String FILE_NOT_FOUND = "404.html";
	static final String METHOD_NOT_SUPPORTED = "no_support.html";
	// port to listen connection
	static final int PORT = 8080;
	
	// verbose mode
	static final boolean verbose = true;
	
	// Client Connection via Socket Class
	private Socket connect;
	
	public JavaHTTPServer(Socket c) {
		connect = c;
	}
	
	
	@Override
	public void run() {
		// we manage our particular client connection
		BufferedReader in = null; PrintWriter out = null; BufferedOutputStream dataOut = null;
		String fileRequested = null;
		
		try {
			// we read characters from the client via input stream on the socket
			in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			
			// we get character output stream to client (for headers)
			out = new PrintWriter(connect.getOutputStream());
			
			// get binary output stream to client (for requested data)
			dataOut = new BufferedOutputStream(connect.getOutputStream());
			
			// get first line of the request from the client
			String input = in.readLine();
			
			// we parse the request with a string tokenizer
			StringTokenizer parse = new StringTokenizer(input);
			
			String method = parse.nextToken().toUpperCase(); // we get the HTTP method of the client
			
			// we get file requested
			fileRequested = parse.nextToken().toLowerCase();
			
			// we support only GET and HEAD methods, we check
			if (!method.equals("GET")  &&  !method.equals("HEAD")) {
				if (verbose) {
					System.out.println("501 Not Implemented : " + method + " method.");
				}
				
				// we return the not supported file to the client
				File file = new File(WEB_ROOT, METHOD_NOT_SUPPORTED);
				int fileLength = (int) file.length();
				String contentMimeType = "text/html";
				//read content to return to client
				
					
				// we send HTTP Headers with data to client
				out.println("HTTP/1.1 501 Not Implemented");
				
				// following 3 header lines are optional
				out.println("Date: " + new Date());
				out.println("Content-type: " + contentMimeType);
				out.println("Content-length: " + fileLength);
				
				out.println(); // blank line between headers and content, very important !
				out.flush(); // flush character output stream buffer. Not needed if create with 'true' i.e., =new PrintWriter(connect.getOutputStream(), true);
				
				//read file, line by line
				BufferedReader rf = new BufferedReader(new FileReader (file));
				String line;
				while ( (line = rf.readLine()) != null)
				{
					out.println(line);
					out.flush();
		
         		}

			
			} else {
				// GET or HEAD method
				if (fileRequested.endsWith("/")) {
					fileRequested += DEFAULT_FILE;
				}
				
				File file = new File(WEB_ROOT, fileRequested);
				if (! file.exists()){

					file = new File(WEB_ROOT, FILE_NOT_FOUND);
					int fileLength = (int) file.length();
					String content = "text/html";
					

					out.println("HTTP/1.1 404 File Not Found");

					// following 3 header lines are optional
					out.println("Date: " + new Date());
					out.println("Content-type: " + content);
					out.println("Content-length: " + fileLength);

					out.println(); // blank line between headers and content, very important !
					out.flush(); // flush character output stream buffer

 					BufferedReader rf = new BufferedReader(new FileReader (file));
					String line;
					while ( (line = rf.readLine()) != null)
					{
						out.println(line);
						out.flush();
					}


					if (verbose) {
						System.out.println("File " + fileRequested + " not found");
		            }
			
				}
				else{ // GET, HEAD, file exists
	
				
				int fileLength = (int) file.length();
				String content = "text/html";  //getContentType(fileRequested);
				
				// send HTTP Headers
				out.println("HTTP/1.1 200 OK");

				// optional 3 header lines
				out.println("Date: " + new Date());
				out.println("Content-type: " + content);
				out.println("Content-length: " + fileLength);

				out.println(); // blank line between headers and content, very important !
				out.flush(); // flush character output stream buffer
				
				if (method.equals("GET")) { // GET method so we return content of file by reading line by line
					
					BufferedReader rf = new BufferedReader(new FileReader (file));
					String line;
					while ( (line = rf.readLine()) != null)
					{
						out.println(line);
						out.flush();
					}

				 
				}
				
				if (verbose) {
					System.out.println("File " + fileRequested + " of type " + content + " returned");
				}
			  }	
			}
			
		}   catch (IOException ioe) {
			System.err.println("Server error : " + ioe);
		} finally {
			try {
				in.close();
				out.close();
				dataOut.close();
				connect.close(); // we close socket connection
			} catch (Exception e) {
				System.err.println("Error closing stream : " + e.getMessage());
			} 
			
			if (verbose) {
				System.out.println("Connection closed.\n");
			}
		}
		
		
	}
	
 
	 
	public static void main(String[] args) {
		try {
			ServerSocket serverConnect = new ServerSocket(PORT);
			System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
			
			// we listen until user halts server execution
			while (true) {
				JavaHTTPServer myServer = new JavaHTTPServer(serverConnect.accept());
				
				if (verbose) {
					System.out.println("Connecton opened. (" + new Date() + ")");
				}
				
				// create dedicated thread to manage the client connection
				Thread thread = new Thread(myServer);
				thread.start();
			}
			
		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		}
	}

}