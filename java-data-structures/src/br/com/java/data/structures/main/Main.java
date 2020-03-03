package br.com.java.data.structures.main;

import br.com.java.data.structures.QueueDataStructure;
import br.com.java.data.structures.StackDataStructure;

public class Main {

	public static void main(String[] args) {
		System.out.println("----- STACK (PILHA) ------");
		StackDataStructure<Integer> pilha = new StackDataStructure<Integer>();
		pilha.add(25);
		pilha.add(65);
		pilha.add(87);
		pilha.add(14);
		pilha.add(30);  
		System.out.println(pilha);
		
		System.out.println("----- QUEUE (FILA) ------");
		QueueDataStructure<Integer> fila = new QueueDataStructure<Integer>();
		
		fila.add(41);
		fila.add(28);
		fila.add(74);
		fila.add(89);
		fila.add(14);
		
		System.out.println(fila);
		
	}

}
