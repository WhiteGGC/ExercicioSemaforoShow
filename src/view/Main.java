package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBilheteria;

public class Main {

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		for(int i=0;i<300;i++){
			Thread tBilheteria = new ThreadBilheteria(i, semaforo);
			tBilheteria.start();
		}

	}

}
