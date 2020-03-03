package br.com.java.data.structures;

public class DequeDataStructure<T extends Object> implements DataStructures<T>{
	private Node<T> head = null;
	private Node<T> syrup = null;
	private int length = 0;
	
	public DequeDataStructure() {
		head = new Node<T>();
		syrup = new Node<T>();
		
		head.setProx(syrup);
		head.setAnt(syrup);
		syrup.setProx(head);
		syrup.setAnt(head);
	}

	@Override
	public void add(T value) {
	}
	
	public void addFront(T value) {
		Node<T> novo = new Node<T>();
		novo.setValue(value);
		length++;
		if(isEmpty()) {
			head.setProx(novo);
			novo.setAnt(head);
			novo.setProx(syrup);
			syrup.setAnt(novo);
		}else {
			Node<T> aux = head.getProx();
			novo.setAnt(head);
			novo.setProx(aux);
			aux.setAnt(novo);
			head.setProx(novo);
		}
	}
	
	public void addBack(T value) {
		Node<T> novo = new Node<T>();
		novo.setValue(value);
		length++;
		if(isEmpty()) {
			head.setProx(novo);
			novo.setAnt(head);
			novo.setProx(syrup);
			syrup.setAnt(novo);
		}else {
			Node<T> aux = syrup.getAnt();
			novo.setProx(syrup);
			novo.setAnt(aux);
			aux.setProx(novo);
			syrup.setAnt(novo);
		}
	}

	public void removeBack() {
		length--;
		Node<T> aux = syrup.getAnt();
		Node<T> aux2 = aux.getAnt();
		aux2.setProx(syrup);
		syrup.setAnt(aux2);
	}
	
	public void removeFront() {
		Node<T> aux = head.getProx();
		Node<T> aux2 = aux.getProx();
		head.setProx(aux2);
		aux2.setAnt(head);
		length--;
	}
	
	public T back() {
		return syrup.getAnt().getValue();
	}
	
	public T front() {
		return head.getProx().getValue();
	}
	
	@Override
	public void remove() {
		
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public boolean isEmpty() {
		if(head.getProx() == syrup) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String str = "";
		Node<T> aux = head.getProx();
		
		while(aux != syrup) {
			str += aux.getValue() + " ";
			aux = aux.getProx();
		}
		
		return str;
	}
}
