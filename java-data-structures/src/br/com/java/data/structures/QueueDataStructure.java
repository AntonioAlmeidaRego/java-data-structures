package br.com.java.data.structures;

public class QueueDataStructure<T extends Object> implements DataStructures<T> {
	private Node<T> head = null;
	private Node<T> syrup = null;
	private int length = 0;
	
	public QueueDataStructure() {
		head = new Node<T>();
		syrup = new Node<T>();
		
		head.setProx(syrup);
		head.setAnt(syrup);
		syrup.setProx(head);
		syrup.setAnt(head);
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
		Node<T> aux = head.getProx();
		Node<T> aux2 = aux.getProx();
		head.setProx(aux2);
		aux2.setAnt(head);
		length--;
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

	@Override
	public int size() {
		return length;
	}
	
	public T front() {
		return head.getProx().getValue();
	}
	
	public T back() {
		return syrup.getAnt().getValue();
	}

	@Override
	public boolean isEmpty() {
		if(head.getProx() == syrup) {
			return true;
		}
		return false;
	}

}
