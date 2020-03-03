package br.com.java.data.structures.main;

import br.com.java.data.structures.StackDataStructure;

public class Main {

	public static void main(String[] args) {
		StackDataStructure<Integer> pilha = new StackDataStructure<Integer>();
		pilha.add(25);
		pilha.add(65);
		pilha.add(87);
		pilha.add(14);
		pilha.add(30);  
		System.out.println(pilha);
	}

}
