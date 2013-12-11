package serpis.psp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.TimeZone;


public class UdpServer {

	static final int MaxPaquetSize = 2048; //tamaño maximo de un paquete udp es de 2^16
	private static final String LOG_FILE_NAME= "UdpServer.log";
	private static final String LOG_CLIENT ="[%1$tF %1$tT  UTC] client %2$s %3$s length %4$s message %5$s \n";
	private static final String LOG_START ="[%1$tF %1$tT  UTC] %2$s \n";
	private static final String LOG_END = "[%1$tF %1$tT  UTC] %2$s \n";
	public static void main(String[] args) throws IOException {
		
		DatagramSocket  socket = new DatagramSocket(12345);

		final PrintWriter logPrintWriter = new PrintWriter(new FileOutputStream(LOG_FILE_NAME,true),true);
		
		logPrintWriter.printf(LOG_START, Calendar.getInstance(TimeZone.getTimeZone("UTC")),"udp server starts");
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run() {
				logPrintWriter.printf(LOG_END,Calendar.getInstance(TimeZone.getTimeZone("UTC")),"udp server ends");
				logPrintWriter.flush();
				logPrintWriter.close();
			
			}
		});
		
		
		while(true){
			byte[] inBuf = new byte[ MaxPaquetSize ];
			int inLength = inBuf.length;
			DatagramPacket inDatagramPacket = new DatagramPacket(inBuf, inLength);

			socket.receive(inDatagramPacket); //aquí espera hasta recibir
			
			String inMessage = new String(inBuf, 0, inDatagramPacket.getLength());
			
			logPrintWriter.printf(LOG_CLIENT,Calendar.getInstance(TimeZone.getTimeZone("UTC")), inDatagramPacket.getAddress(),inDatagramPacket.getPort(),inMessage.length(),inMessage);

			String outMessage = inMessage.toUpperCase();
			
			byte[] outBuf = outMessage.getBytes();
			int outLength = outBuf.length;
			InetAddress outInetAddress = inDatagramPacket.getAddress(); //ip del remitente
			int outPort = inDatagramPacket.getPort(); //port del remitente
			DatagramPacket outDatagramPacket = 
					new DatagramPacket(outBuf, outLength, outInetAddress, outPort);
			socket.send(outDatagramPacket);
					
			System.out.println("UdpServer end.");
	
			
		}
	}

}
