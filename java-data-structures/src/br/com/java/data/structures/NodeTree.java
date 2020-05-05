package br.com.java.data.structures;

public class NodeTree<T extends Object> {
	private NodeTree<T> left = null;
	private NodeTree<T> right = null;
	private NodeTree<T> father = null;
	private T value;
	
	public NodeTree(T value) {
		this.value = value;
	}
	
	public NodeTree() {
	}
	
	public NodeTree<T> getLeft() {
		return left;
	}
	
	public void setLeft(NodeTree<T> left) {
		this.left = left;
	}
	
	public NodeTree<T> getRight() {
		return right;
	}
	
	public void setRight(NodeTree<T> right) {
		this.right = right;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}

	public NodeTree<T> getFather() {
		return father;
	}

	public void setFather(NodeTree<T> father) {
		this.father = father;
	}
	
	
	 
}
