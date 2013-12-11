package serpis.psp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.server.ServerNotActiveException;
import java.util.List;
import java.util.Scanner;


public class TcpClient {
	private static final String SERVER_IP="127.0.0.1";
	private static final int SERVER_PORT =12345;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		
		InetAddress inetAddress= InetAddress.getByName(SERVER_IP);
		Socket socket = new Socket(inetAddress,SERVER_PORT);
		System.out.printf("TCP Client serverName=%1$s port=%2$s",SERVER_IP,SERVER_PORT);
		System.out.println();
		String fileName = args [0];
		//Files.readAllBytes(path);
		String lineOut ="Hola";
		List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		PrintWriter printWriter= new PrintWriter(socket.getOutputStream());
		
		
		printWriter.printf("%s\n",lineOut);
		printWriter.flush();
		
		Scanner scanner= new Scanner (socket.getInputStream());
		
		String lineIn = scanner.nextLine();
		System.out.printf("Recibido = '%s'", lineIn);
		System.out.println();
		
		System.out.println("TcpClient end.");
		socket.close();
	}

}
