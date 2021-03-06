package br.com.java.data.structures;

import br.com.java.data.structures.util.Util;

public class QueueDataStructure<T extends Object> implements DataStructures<T> {
	private Node<T> head = null;
	private Node<T> syrup = null;
	private int length = 0;
	private int pos = -1;
	private Util<T> util = new Util<T>();
	private String typeObject = "";
	
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
		typeObject = value.getClass().getSimpleName();
		Node<T> novo = new Node<T>();
		novo.setValue(value);
		length++;
		pos++;
		novo.setPos(pos);
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
		setPos();
	}
	
	public void remove(int i) {
		Node<T> node = get(i);
		if(node != null) {
			if(node.getPos() == i) {
				Node<T> ant = node.getAnt();
				Node<T> prox = node.getProx();
				ant.setProx(prox);
				prox.setAnt(ant);
				length--;
				setPos();
			}
		}
	}
	
	public void remove(T value, int i) {
		Node<T> node = search(value);
		if(node != null) {
			if(node.getPos() == i) {
				Node<T> ant = node.getAnt();
				Node<T> prox = node.getProx();
				ant.setProx(prox);
				prox.setAnt(ant);
				length--;
				setPos();
			}
		}
	} 
	
	public void remove(T value) {
		Node<T> node = search(value);
		if(node != null) {
			Node<T> ant = node.getAnt();
			Node<T> prox = node.getProx();
			ant.setProx(prox);
			prox.setAnt(ant);
			length--;
			setPos();
		}
	}
	
	public void update(T novo, int i) {
		Node<T> node = get(i);
		if(node != null) {
			if(node.getPos() == i) {
				node.setValue(novo);
			}
		}
	}
	
	public void update(T novo, T ant) {
		Node<T> node = search(ant);
		if(node != null) {
			node.setValue(novo);
		}
	}
	
	
	
	private void setPos() {
		Node<T> aux = head.getProx();
		int pos = 0;
		while(aux != syrup) {
			aux.setPos(pos); 
			aux = aux.getProx();
			pos++;
		}
	}
	
	public Node<T> get(int i){
		Node<T> aux = head.getProx();
		while(aux != syrup) {
			if(aux.getPos() == i) {
				return aux;
			}
			aux = aux.getProx();
		}
		return null;
	}
	
	public Node<T> search(T value){
		Node<T> aux = head.getProx();
		while(aux != syrup) {
			if(util.parseNumberLong(value) == util.parseNumberLong(aux.getValue())) {
				return aux;
			}
			aux = aux.getProx();
		}
		return null;
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
	
	@Override
	public T[] extractValues() {
		if(typeObject.equalsIgnoreCase("integer")) {
			Object[] array = new Integer[size()];
			Node<T> aux = head.getProx();
			int index = 0;
			while(aux != syrup) {
				array[index] = util.parseNumberInteger(aux.getValue());
				aux = aux.getAnt();
				index++;
			}
			
			return (T[]) array;
		}
		return null;
	}

}
