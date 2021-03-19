package controller;

import java.util.concurrent.Semaphore;

public class ThreadBilheteria extends Thread{
	private int Id;
	private Semaphore semaforo;
	private static int ingressos = 100;
	private int tempog;
	
	public ThreadBilheteria(int Id, Semaphore semaforo){
		this.Id = Id;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		tempog = Login();
		if(tempog <= 1000){
			tempog = Compra();
			if(tempog <= 2500){
//				-----Seção Critica------
				try {
					semaforo.acquire();
					Validacao();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					semaforo.release();
				}
//				-----Fim Seção Critica-----
			}
		}		
	}
	
	private int Login(){
		int tempo = (int) (Math.random() * 1951 + 50);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(tempo > 1000){
			System.out.println("A pessoa "+Id+" saiu por Timeout");
		}
		return tempo;
	}
	
	private int Compra(){
		int tempo = (int) (Math.random() * 2001 + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(tempo > 2500){
			System.out.println("A pessoa "+Id+" estourou o tempo da seção");
		}
		return tempo;
	}
	
	private void Validacao(){
		int compra = (int) (Math.random() * 4 + 1);
		if(ingressos < compra){
			System.out.println("Não temos ingressos o suficiente para a compra da pessoa "+Id);
		}else{
			ingressos = ingressos - compra;
			System.out.println("A pessoa "+Id+" comprou "+compra+" ingressos, ainda temos: "+ingressos+" ingressos");
		}
	}
}
