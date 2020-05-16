package br.com.java.data.structures.main;

import br.com.java.data.structures.DequeDataStructure;
import br.com.java.data.structures.Percurso;
import br.com.java.data.structures.QueueDataStructure;
import br.com.java.data.structures.StackDataStructure;
import br.com.java.data.structures.TreeBinaryDataStructure;

public class Main {
	
	public static void printStack() {
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
	}
	
	public static void printQueue(){
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
	}
	
	public static void printDeque() {
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
		
		System.out.println();
	}
	
	public static void printTreeBinary() {
		System.out.println("----- TREE BINARY (ÁRVORE BINARIA) ------");
		
		Percurso<Integer> percurso = new Percurso<Integer>();
		TreeBinaryDataStructure<Integer> binaryDataStructure = new TreeBinaryDataStructure<Integer>(50); 
		binaryDataStructure.add(48);
		binaryDataStructure.add(49);
		binaryDataStructure.add(47);
		binaryDataStructure.add(46);
		binaryDataStructure.add(45);
		binaryDataStructure.add(43);
		binaryDataStructure.add(44);
		binaryDataStructure.add(42);
		binaryDataStructure.add(53);
		binaryDataStructure.add(51);
		binaryDataStructure.add(55);
		binaryDataStructure.add(56);
		binaryDataStructure.add(60);
		binaryDataStructure.add(61);
		binaryDataStructure.add(58);
		System.out.println();
		System.out.println("--------- APOS INSERÇÃO ------------");
		System.out.println();
		System.out.println("---- Pré-Ordem -----");
		System.out.println();
		percurso.preOrdem(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println();
		System.out.println("---- Pós-Ordem -----");
		System.out.println();
		percurso.posOrdem(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println();
		System.out.println("----- Ordem Simetrica -----");
		System.out.println();
		percurso.ordemSimetrica(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println();
		System.out.println("----- Nível -----");
		System.out.println();
		percurso.nivel(binaryDataStructure.getRoot(), binaryDataStructure);
		System.out.println();
		System.out.println();
		System.out.println("SIZE " + binaryDataStructure.size());
		System.out.println();
		System.out.println();
		System.out.println("--------- APOS REMOÇÃO ------------");
		binaryDataStructure.remove(binaryDataStructure.getRoot(), 48); 
		binaryDataStructure.remove(binaryDataStructure.getRoot(), 60);
		System.out.println();
		System.out.println("---- Pré-Ordem -----");
		System.out.println();
		percurso.preOrdem(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println();
		System.out.println("---- Pós-Ordem -----");
		System.out.println();
		percurso.posOrdem(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println();
		System.out.println("----- Ordem Simetrica -----");
		System.out.println();
		percurso.ordemSimetrica(binaryDataStructure.getRoot());
		System.out.println();
		System.out.println();
		System.out.println("----- Nível -----");
		System.out.println();
		percurso.nivel(binaryDataStructure.getRoot(), binaryDataStructure);
		System.out.println();
		System.out.println();
		System.out.println("SIZE " + binaryDataStructure.size());
		System.out.println();
		System.out.println();
		
	}

	public static void main(String[] args) throws Exception{
		printStack();
		printQueue();
		printDeque();
		printTreeBinary();
		
	}

}
