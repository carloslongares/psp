package serpis.psp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpServer {
	//private static final String SERVER_IP="127.0.0.1";
	private static final int SERVER_PORT =12345;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.printf("TcpServer port =%s\n",SERVER_PORT);
		
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		
		Socket socket = serverSocket.accept();
		
		Scanner scanner= new Scanner (socket.getInputStream());
		while (true){
			String lineIn = scanner.nextLine();
			if (lineIn.equals("."))
				break;
			
			String lineOut =lineIn.toUpperCase();
			PrintWriter printWriter= new PrintWriter(socket.getOutputStream());
			printWriter.printf("%s\n",lineOut);
			printWriter.flush();
			
			
			System.out.printf("Recibido = '%s'", lineOut);
			System.out.println();
		}
		System.out.println("Tcp Server end.");
		
		socket.close();
	}

}