package br.com.java.data.structures;

import java.sql.Array;
import java.util.ArrayList;

import br.com.java.data.structures.util.Util;

public class StackDataStructure<T extends Object> implements DataStructures<T>{
	private Node<T> head = null;
	private Node<T> syrup = null;
	private int length = 0;
	private Util<T> util = new Util<T>();
	private int pos = -1;
	private String typeObject = "";
	
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
		this.typeObject = value.getClass().getSimpleName();
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
		length--;
		Node<T> aux = syrup.getAnt();
		Node<T> aux2 = aux.getAnt();
		aux2.setProx(syrup);
		syrup.setAnt(aux2);
		setPos();
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
	
	public T topo() {
		return syrup.getAnt().getValue();
	}
	
	public T back() {
		return head.getProx().getValue();
	}
	
	public Node<T> get(int i){
		Node<T> aux = syrup.getAnt();
		while(aux != head) {
			if(aux.getPos() == i) {
				return aux;
			}
			aux = aux.getAnt();
		}
		return null;
	}
	
	private void setPos() {
		Node<T> aux = syrup.getAnt();
		int pos = 0;
		while(aux != head) {
			aux.setPos(pos); 
			aux = aux.getAnt();
			pos++;
		}
	}
	
	public Node<T> search(T value){
		Node<T> aux = syrup.getAnt();
		while(aux != head) {
			if(util.parseNumberLong(value) == util.parseNumberLong(aux.getValue())) {
				return aux;
			}
			aux = aux.getAnt();
		}
		return null;
	}
	
	@Override
	public T[] extractValues() {
		if(typeObject.equalsIgnoreCase("integer")) {
			Object[] array = new Integer[size()];
			Node<T> aux = syrup.getAnt();
			int index = 0;
			while(aux != head) {
				array[index] = util.parseNumberInteger(aux.getValue());
				aux = aux.getAnt();
				index++;
			}
			return (T[]) array;
		}
		return null;
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
