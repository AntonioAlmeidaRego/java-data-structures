package br.com.java.data.structures.main;

import br.com.java.data.structures.DequeDataStructure;
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
		System.out.println("TOPO: "+ pilha.topo());
		System.out.println("BACK: "+ pilha.back());
		System.out.println("SIZE: "+ pilha.size());
		System.out.println(pilha);
		
		System.out.println("----- QUEUE (FILA) ------");
		QueueDataStructure<Integer> fila = new QueueDataStructure<Integer>();
		
		fila.add(41);
		fila.add(28);
		fila.add(74);
		fila.add(89);
		fila.add(14);  
		System.out.println("FRONT: " + fila.front());
		System.out.println("BACK: " + fila.back());
		System.out.println("SIZE: " + fila.size());
		System.out.println(fila); 
		
		System.out.println();
		System.out.println("----- Double-ended queue (DEQUE) ------");
		DequeDataStructure<Integer> deque = new DequeDataStructure<Integer>();
		deque.addBack(62);
		deque.addBack(12);
		deque.addFront(74);
		deque.addFront(10);
		deque.addFront(79); 

		System.out.println("FRONT: " + deque.front());
		System.out.println("BACK: " + deque.back());
		System.out.println("SIZE: " + deque.size());
		
		System.out.println(deque);
		
	}

}
