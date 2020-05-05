package br.com.java.data.structures;

import java.io.Console;

public class TreeBinaryDataStructure<T extends Object> implements DataStructures<T>{
	
	private NodeTree<T> root = new NodeTree<T>();
	private int length = 0;
	private boolean isExist = false;
	private QueueDataStructure<NodeTree<T>> fila = new QueueDataStructure<NodeTree<T>>();
	
	public TreeBinaryDataStructure(T value) {
		this.root.setValue(value);
	}
	
	private NodeTree<T> lastChild(NodeTree<T> root) {
		if(root.getLeft() != null) {
			return lastChild(root.getLeft());
		}
		if(root.getRight() != null) {
			return lastChild(root.getRight());
		}
		return root;
	}
	
	@Override
	public void add(T value) {
		final String type = value.getClass().getGenericSuperclass().getTypeName().substring(10, value.getClass().getGenericSuperclass().getTypeName().length());
		if(type.equals("Number")) {
			Number numberParm = (Number) value;
			Number numberRoot = (Number) root.getValue();
			if((isEmptyLeft()) && (numberParm.longValue() < numberRoot.longValue())) {
				NodeTree<T> node = new NodeTree<T>(value);
				node.setFather(root);
				root.setLeft(node);
			}else if((isEmptyRight()) && (numberParm.floatValue() > numberRoot.longValue())) {
				NodeTree<T> node = new NodeTree<T>(value);
				node.setFather(root);
				root.setRight(node);
			}else {
				if(numberParm.longValue() < numberRoot.longValue()) {
					NodeTree<T> last = lastChild(root.getLeft());
					Number number = (Number) last.getValue();
					if(numberParm.longValue() > number.longValue()) {
						NodeTree<T> node = new NodeTree<T>(value);
						node.setFather(last);
						last.setRight(node);
					}else {
						NodeTree<T> node = new NodeTree<T>(value);
						node.setFather(last);
						last.setLeft(node);
					}
				}else {
					NodeTree<T> last = lastChild(root.getRight());
					Number number = (Number) last.getValue();
					if(numberParm.longValue() > number.longValue()) {
						NodeTree<T> node = new NodeTree<T>(value);
						node.setFather(last);
						last.setRight(node);
					}else {
						NodeTree<T> node = new NodeTree<T>(value);
						node.setFather(last);
						last.setLeft(node);
					}
				}
			}
		}
		
	}
	
	public NodeTree<T> getRoot(){
		return root;
	}
	
	public void preOrdem(NodeTree<T> root) {
		visitor(root);
		if(root.getLeft() != null) {
			preOrdem(root.getLeft());
		}
		if(root.getRight() != null) {
			preOrdem(root.getRight());
		}
	}
	
	public void posOrdem(NodeTree<T> root) {
		if(root.getLeft() != null) {
			posOrdem(root.getLeft());
		}
		if(root.getRight() != null) {
			posOrdem(root.getRight());
		}
		visitor(root);
	}
	
	public void ordemSimetrica(NodeTree<T> root) {
		if(root.getLeft() != null) {
			ordemSimetrica(root.getLeft());
		}
		visitor(root);
		if(root.getRight() != null) {
			ordemSimetrica(root.getRight());
		}
	} 
	
	public void nivel(NodeTree<T> root, TreeBinaryDataStructure<T> tree) {
		if(root.equals(tree.getRoot())) {
			fila.add(root);
		} 
		if(!fila.isEmpty()) {
			if(fila.front().getLeft() != null) {
				fila.add(fila.front().getLeft());
			}
			if(fila.front().getRight() != null) {
				fila.add(fila.front().getRight());
			}
			
			visitor(fila.front());
			fila.remove();
			if(fila.front() != null) {
				nivel(fila.front(), tree);
			}
		}
	}
	
	private void visitor(NodeTree<T> nodeTree) {
		System.out.print(nodeTree.getValue() + " ");
	}

	@Override
	public void remove() throws Exception {
		throw new Exception("Method not usa"); 
	}
	
	private NodeTree<T> searchNode(NodeTree<T> root, T value){
		Number numberParm = (Number) value;
		Number numberRoot = (Number) root.getValue();
		
		if(root.equals(this.root)) {
			if(numberParm.longValue() > numberRoot.longValue()) {
				root = root.getRight();
			}else if(numberParm.longValue() < numberRoot.longValue()){
				root = root.getLeft();
			}else {
				return root;
			}
		}
		
		if(root.getValue().equals(value)) {
			return root;
		}
		
		if(root.getLeft() != null) {
			return searchNode(root.getLeft(), value);
		}
		
		if(root.getRight() != null) {
			return searchNode(root.getRight(), value);
		}  
		
		return root;
	}
	
	public void remove(NodeTree<T> root, T value) {
		NodeTree<T> nodeTree = searchNode(root, value);
		
		if(nodeTree != null) {
			 Number number = (Number) nodeTree.getValue();
			 NodeTree<T> childLeft = nodeTree.getLeft();
			 NodeTree<T> childRight = nodeTree.getRight();
			 Number numberChildLeft = childLeft != null ? (Number) nodeTree.getLeft().getValue() : -1;
			 Number numberChildRight = childRight != null ? (Number) nodeTree.getRight().getValue() : -1;
			 NodeTree<T> nodeFather = nodeTree.getFather();
			 if(nodeFather != null) {
				 if(numberChildRight.longValue() > -1) {
					 childRight.setFather(nodeFather);
					 nodeFather.setRight(childRight);
					 if(numberChildLeft.longValue() > -1) {
						childLeft.setFather(childRight); 
						childRight.setLeft(childLeft);
					 }
				 }
			 }
		}
	}

	@Override
	public int size() {
		return 0;
	}

	public boolean isEmptyLeft() {
		return root.getLeft() == null ? true : false;
	}
	
	public boolean isEmptyRight() {
		return root.getRight() == null ? true : false;
	}
	
	@Override
	public boolean isEmpty() throws Exception {
		throw new Exception("Method not usa"); 
	}

}
