package br.com.java.data.structures;

import br.com.java.data.structures.util.Search;
import br.com.java.data.structures.util.Util;

public class TreeBinaryDataStructure<T extends Object> implements DataStructures<T>{
	
	private NodeTree<T> root = new NodeTree<T>();
	private Integer size = 0;
	private NodeTree<T> nodeTreeAux = null; 
	private StackDataStructure<T> stackDataStructure = new StackDataStructure<T>();
	private Util<T> util = new Util<T>();
	
	public TreeBinaryDataStructure(T value) { 
		this.root.setValue(value);
		this.stackDataStructure.add(value);
		size++;
	}
	
	private NodeTree<T> child(NodeTree<T> root, T value) {
		long numberParm = ((Number) value).longValue();
		long numberRoot = ((Number) root.getValue()).longValue();
		
		if((numberParm < numberRoot) && !isChildLeft(root)) {
			return root;
		}
		
		if((numberParm > numberRoot) && !isChildRight(root)) {
			return root;
		}
		
		if((numberParm < numberRoot) && (root.getLeft() != null)) {
			return child(root.getLeft(), value);
		}
		
		if((numberParm > numberRoot) && (root.getRight() != null)) {
			return child(root.getRight(), value);
		}
		
		return root;
	}
	
	@Override
	public void add(T value) {
		size++;
		final String type = value.getClass().getGenericSuperclass().getTypeName().substring(10, value.getClass().getGenericSuperclass().getTypeName().length());
		if(type.equals("Number")) {
			stackDataStructure.add(value);
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
					NodeTree<T> last = child(root.getLeft(), value);
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
					NodeTree<T> last = child(root.getRight(), value);
					if(last != null) {
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
	}
	
	public NodeTree<T> getRoot(){
		return root;
	}
	
	@Override
	public void remove() throws Exception {
		throw new Exception("unused method"); 
	}
	
	public NodeTree<T> searchNode(NodeTree<T> root, T value){
		Number numberParm = (Number) value;
		Number numberRoot = (Number) root.getValue();
		if(root.equals(this.root)) {
			if(numberParm.longValue() > numberRoot.longValue()) {
				root = root.getRight();
				nodeTreeAux = root;
			}else if(numberParm.longValue() < numberRoot.longValue()){
				root = root.getLeft();
				nodeTreeAux = root;
			}else {
				return root;
			}
		}

		if(util.parseNumberLong(nodeTreeAux.getValue()) == util.parseNumberLong(value)) {
			return nodeTreeAux;
		}else {
			if(root.getLeft() != null && util.parseNumberLong(nodeTreeAux.getValue()) != util.parseNumberLong(value)) {
				nodeTreeAux = root.getLeft();
				searchNode(root.getLeft(), value);
			}
			if(root.getRight() != null && util.parseNumberLong(nodeTreeAux.getValue()) != util.parseNumberLong(value)) {
				nodeTreeAux = root.getRight();
				searchNode(root.getRight(), value);
			}  
		} 
		
		return nodeTreeAux;
	}
	
	private boolean isChildLeft(NodeTree<T> node) {
		return node.getLeft() != null ? true : false;
	}
	
	private boolean isChildRight(NodeTree<T> node) {
		return node.getRight() != null ? true : false;
	}
	
	private NodeTree<T> searchMin(NodeTree<T> root, T value){
		Search<T> search = new Search<T>(stackDataStructure.extractValues());
		Number numberParm = (Number) value;
		Number numberRoot = (Number) root.getValue();
		if(root.equals(this.root)) {
			NodeTree<T> nodeSearch = searchNode(root, value);
			if(!isChildLeft(nodeSearch) && !isChildRight(nodeSearch)) {
				return null;
			} 
			if(numberParm.longValue() > numberRoot.longValue()) {
				root = root.getRight();
				nodeTreeAux = root;
			}else if(numberParm.longValue() < numberRoot.longValue()){
				root = root.getLeft();
				nodeTreeAux = root;
			}else {
				return root;
			}
		}
		
		long seach = search.searchForPreviousValue(value) != null ? ((Number) search.searchForPreviousValue(value)).longValue() : -1;
		if(util.parseNumberLong(nodeTreeAux.getValue()) == seach) {
			return nodeTreeAux;
		}
		
		if(root.getLeft() != null && util.parseNumberLong(nodeTreeAux.getValue()) != seach) {
			nodeTreeAux = root.getLeft();
			searchMin(root.getLeft(), value);
		}
		if(root.getRight() != null && util.parseNumberLong(nodeTreeAux.getValue()) != seach) {
			nodeTreeAux = root.getRight();
			searchMin(root.getRight(), value);
		}  
		
		return nodeTreeAux;
	}
	
	public T[] extractValues() {
		return stackDataStructure.extractValues();
	}
	 
	
	public void remove(NodeTree<T> root, T value) {
		size--;
		NodeTree<T> nodeTree = searchNode(root, value);
		if(nodeTree != null) { 
			 stackDataStructure.remove(value);
			 NodeTree<T> nodeFather = nodeTree.getFather();
			 if(nodeFather != null) { 
				 if(isChildLeft(nodeTree) && isChildRight(nodeTree)) {
					 NodeTree<T> nodeMin = searchMin(root, value);
					 long numberFather = ((Number) nodeFather.getValue()).longValue();
					 long numberNodeMin = ((Number) nodeMin.getValue()).longValue();
					 long numberRemove = ((Number) nodeTree.getValue()).longValue();
					 nodeMin.setFather(nodeFather);
					 if(numberRemove > numberNodeMin) {
						 nodeMin.setRight(nodeTree.getRight());
						 nodeTree.getRight().setFather(nodeMin);
					 }else {
						 nodeTree.getLeft().setFather(nodeMin);
						 nodeMin.setLeft(nodeTree.getLeft());
				 	 }
					 if(numberFather > numberNodeMin) {
						 nodeFather.setLeft(nodeMin);
					 }else {
						 nodeFather.setRight(nodeMin);
					 }
					 
				 }else {
					  if(!isChildLeft(nodeTree) && !isChildRight(nodeTree)) {
						  if(((Number) nodeTree.getValue()).longValue() < ((Number) nodeFather.getValue()).longValue()) {
							  nodeFather.setLeft(null);
						  }else {
							  nodeFather.setRight(null);
						  }
					  }else {
						  if(((Number) nodeTree.getValue()).longValue() < ((Number) nodeFather.getValue()).longValue()) {
							  if(!isChildLeft(nodeTree) && isChildRight(nodeTree)) {
								  nodeTree.getRight().setFather(nodeFather);
								  nodeFather.setLeft(nodeTree.getRight());
							  }else {
								  nodeTree.getLeft().setFather(nodeFather);
								  nodeFather.setLeft(nodeTree.getLeft());
							  }
						  }else {
							  if(!isChildLeft(nodeTree) && isChildRight(nodeTree)) {
								  nodeTree.getRight().setFather(nodeFather);
								  nodeFather.setRight(nodeTree.getRight());
							  }else {
								  nodeTree.getRight().setFather(nodeFather);
								  nodeFather.setRight(nodeTree.getLeft());
							  }
						  }
					  }
				 }
				
			 }
		}
	}

	@Override
	public int size() {
		return size;
	}

	public boolean isEmptyLeft() {
		return root.getLeft() == null ? true : false;
	}
	
	public boolean isEmptyRight() {
		return root.getRight() == null ? true : false;
	}
	
	@Override
	public boolean isEmpty() throws Exception {
		throw new Exception("unused method"); 
	}

}
