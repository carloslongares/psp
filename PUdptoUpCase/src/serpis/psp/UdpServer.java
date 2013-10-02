package serpis.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.net.SocketException;

public class UdpServer {

	static final int MaxPaquetSize = 2048; //tamaño maximo de un paquete udp es de 2^16
	
	
	public static void main(String[] args) throws IOException {
		
		DatagramSocket  socket = new DatagramSocket(12345);

		byte [] buf = new byte[MaxPaquetSize];
		
		DatagramPacket inDatagramPacket = new DatagramPacket(buf,buf.length);
		
		socket.receive(inDatagramPacket); // AQUI ESPERA HASTA RECIBIR
		
		System.out.println("IP de origen= " + inDatagramPacket.getAddress());
		System.out.println ("Puerto de origen= " + inDatagramPacket.getPort());
		
		String mensajeSinProcesar = new String(buf,0,inDatagramPacket.getLength());

		System.out.println(mensajeSinProcesar);
		
		mensajeSinProcesar.toUpperCase();
		buf= mensajeSinProcesar.getBytes();
		DatagramPacket outDataGramPacket = new DatagramPacket  (buf,buf.length,inDatagramPacket.getAddress(),inDatagramPacket.getPort());	
		socket.send(outDataGramPacket);
	}

}
