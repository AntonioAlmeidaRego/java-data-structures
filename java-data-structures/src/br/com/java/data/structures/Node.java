package br.com.java.data.structures;

public class Node <T extends Object> {
	private Node<T> prox = null;
	private Node<T> ant = null;
	private T value;
	
	
	public Node<T> getProx() {
		return prox;
	}
	public void setProx(Node<T> prox) {
		this.prox = prox;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	
	public Node<T> getAnt() {
		return ant;
	}
	
	public void setAnt(Node<T> ant) {
		this.ant = ant;
	}
	 
}
