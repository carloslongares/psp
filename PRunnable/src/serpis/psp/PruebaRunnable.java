package serpis.psp;

import java.util.Random;

public class PruebaRunnable extends Thread {
int hilo;
	/**
	 * @param args
	 */
	//Solucion III
	public static void creaHilo(int hilo){
		class ContadorLC implements Runnable{
			int hiloInterior;
			ContadorLC (int hiloInterior){
				this.hiloInterior= hiloInterior;
			}
			@Override
			public void run() {
				for (int contador = 1; contador <= 10; contador++) {
					System.out.printf("hilo=%s contador=%s\n", hiloInterior, contador);
					try {
						Thread.sleep(  5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}	
			}
			
		}
		ContadorLC contadorLC = new ContadorLC(hilo);
		contadorLC.run();
	}

	
	public static void main(String[] args) {
		System.out.println("main");
		
		//Solución I: ContadorEC
		//		for (int hilo = 1; hilo <= 4; hilo++) {
		//			Thread thread = new Thread(new ContadorEC(hilo));
		//			thread.start();
		//		}
				
		//Solución II: ContadorIC
		//new PruebaRunnable();
		
		
		//Solucion III: ContadorLC
		//creaHilo(1);
		
		
		//SolucionIV: clase anonima
		new Thread() {

			@Override
			public void run() {
				for (int contador = 1; contador <= 10; contador++) {
					System.out.printf("contador=%s\n",  contador);
					try {
						Thread.sleep(  5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}	
				
			}
	    }.start();
		
	  //Solucion V: this implements Runnable
	    new PruebaRunnable(1).start();
	    System.out.println("main end.");
	}

	public PruebaRunnable(int hilo) {
		
		//Solucion V
		this.hilo=hilo;
		
		
		//Solución II: ContadorIC
		//for (int hilo = 1; hilo <= 4; hilo++) {
			//Thread thread = new Thread(new ContadorIC(hilo));
			//thread.start();
		
		}
	
	//Solucion V
	@Override
	public void run() {
		for (int contador = 1; contador <= 10; contador++) {
			System.out.printf("hilo=%s contador=%s\n", hilo, contador);
			try {
				Thread.sleep(  5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		
		}
	
// Solucion II	
//	private class ContadorIC implements Runnable {
//		private int hilo;
//		public ContadorIC(int hilo){
//			this.hilo = hilo;
//		}
//		
//		@Override
//		public void run() {
//			for (int contador = 1; contador <= 10; contador++) {
//				System.out.printf("hilo=%s contador=%s\n", hilo, contador);
//				sleep( getRandomMilis(1000, 5000) );
//			}
//		}
//		
//		private int getRandomMilis(int low, int high) {
//			Random random = new Random();
//			return low + random.nextInt(high - low + 1);
//		}
//		
//		private void sleep(int milis) {
//			try {
//				Thread.sleep(milis);
//			} catch (InterruptedException interruptedException) {
//				//
//			}
//		}
//		
//	}

}