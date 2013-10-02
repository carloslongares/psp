package serpis.psp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClient {

	private static final String hostServer="127.0.0.1";
	public static void main(String[] args) throws IOException {
		
		DatagramSocket  socket = new DatagramSocket();
		
		String mensaje ="hola udp";
		
		byte [] buf = mensaje.getBytes();
		InetAddress host = InetAddress.getByName(hostServer);
		DatagramPacket outDatagramPacket = new DatagramPacket(buf,buf.length,host,12345); 
		
		socket.send(outDatagramPacket);

	
	}

}
