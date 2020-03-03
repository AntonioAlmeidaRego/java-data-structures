package br.com.java.data.structures;

public class StackDataStructure<T extends Object> implements DataStructures<T>{
	private Node<T> head = null;
	private Node<T> syrup = null;
	private int length = 0;
	
	public StackDataStructure() { 
		head = new Node<T>();
		syrup = new Node<T>();
		
		head.setProx(syrup);
		head.setAnt(syrup);
		syrup.setProx(head);
		syrup.setAnt(head);
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
	public void add(T value) {
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
	
	@Override
	public void remove() {
		length--;
		Node<T> aux = syrup.getAnt();
		Node<T> aux2 = aux.getAnt();
		aux2.setProx(syrup);
		syrup.setAnt(aux2);
	}
	
	public T topo() {
		return syrup.getAnt().getValue();
	}
	
	public T back() {
		return head.getProx().getValue();
	}
	
	@Override
	public String toString() {
		String str = "";
		Node<T> aux = syrup.getAnt();
		
		while(aux != head) {
			str += aux.getValue() + "; \n";
			aux = aux.getAnt();
		}
		
		return str;
	}
}
