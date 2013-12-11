package serpis.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UdpClient {
	static final int MaxPaquetSize = 2048;
	private static final String hostServer="127.0.0.1";
	public static void main(String[] args) throws IOException {
		
		DatagramSocket  socket = new DatagramSocket();
		
		String mensaje ="hola udp";
		System.out.println(mensaje);
		byte [] outbuf = mensaje.getBytes();
		InetAddress host = InetAddress.getByName(hostServer);
		DatagramPacket outDatagramPacket = new DatagramPacket(outbuf,outbuf.length,host,12345); 
		
		socket.send(outDatagramPacket);
		
		
		byte[] inBuf = new byte [MaxPaquetSize];
		DatagramPacket inDatagramPacket= new DatagramPacket(inBuf, inBuf.length); 
		socket.receive(inDatagramPacket);
		String inMessage = new String(inBuf,0,inDatagramPacket.getLength());
		System.out.println(inMessage);
		
		
		socket.close();
	
	}

}
