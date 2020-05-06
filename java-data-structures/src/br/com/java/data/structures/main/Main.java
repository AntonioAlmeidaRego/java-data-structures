package br.com.java.data.structures.main;

import br.com.java.data.structures.DequeDataStructure;
import br.com.java.data.structures.QueueDataStructure;
import br.com.java.data.structures.StackDataStructure;
import br.com.java.data.structures.TreeBinaryDataStructure;

public class Main {

	public static void main(String[] args) {
		/*System.out.println("----- STACK (PILHA) ------");
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
		
		System.out.println(deque);*/
		
		TreeBinaryDataStructure<Integer> binaryDataStructure = new TreeBinaryDataStructure<Integer>(25);
		
		binaryDataStructure.add(12);
		binaryDataStructure.add(45);
		binaryDataStructure.add(14);
		binaryDataStructure.add(5);
		binaryDataStructure.add(3);
		binaryDataStructure.add(2);
		binaryDataStructure.add(4);
		binaryDataStructure.add(50);
		binaryDataStructure.add(17);
		/*binaryDataStructure.add(85);
		binaryDataStructure.add(5);
		binaryDataStructure.add(35);*/
		
		//System.out.println(binaryDataStructure.searchNode(binaryDataStructure.getRoot(), 17).getFather().getValue());
		System.out.println();
		System.out.println("Pré-Ordem");
		System.out.println();
		binaryDataStructure.preOrdem(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println();
		System.out.println("Ordem Simetrica");
		System.out.println();
		binaryDataStructure.ordemSimetrica(binaryDataStructure.getRoot());
		/*System.out.println();
		System.out.println("Pos-Ordem");
		binaryDataStructure.posOrdem(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println("Ordem Simetrica");
		binaryDataStructure.ordemSimetrica(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println("Nível");
		binaryDataStructure.nivel(binaryDataStructure.getRoot(), binaryDataStructure);*/
		
		/*binaryDataStructure.remove(binaryDataStructure.getRoot(), 45);
		binaryDataStructure.remove(binaryDataStructure.getRoot(), 12);
		binaryDataStructure.remove(binaryDataStructure.getRoot(), 17);
		binaryDataStructure.remove(binaryDataStructure.getRoot(), 85);
		binaryDataStructure.remove(binaryDataStructure.getRoot(), 50);
		System.out.println("Pré-Ordem");
		binaryDataStructure.preOrdem(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println("Pos-Ordem");
		binaryDataStructure.posOrdem(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println("Ordem Simetrica");
		binaryDataStructure.ordemSimetrica(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println("Nível");
		binaryDataStructure.nivel(binaryDataStructure.getRoot(), binaryDataStructure);*/
	}

}
