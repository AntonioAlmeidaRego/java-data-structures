package br.com.java.data.structures;

public class TreeBinaryDataStructure<T extends Object> implements DataStructures<T>{
	
	private NodeTree<T> root = new NodeTree<T>();
	private NodeTree<T> father = root;
	private QueueDataStructure<NodeTree<T>> fila = new QueueDataStructure<NodeTree<T>>();
	
	public TreeBinaryDataStructure(T value) {
		this.root.setValue(value);
	}
	
	private NodeTree<T> child(NodeTree<T> root, T value) {
		long numberParm = ((Number) value).longValue();
		long numberRoot = ((Number) root.getValue()).longValue();
		
		if(!root.equals(this.root) && (numberParm < numberRoot) && !isChildLeft(root)) {
			return root;
		}
		
		if(!root.equals(this.root) && (numberParm > numberRoot) && !isChildRight(root)) {
			return root;
		}
		
		if(root.getLeft() != null) {
			return child(root.getLeft(), value);
		}
		
		if(root.getRight() != null) {
			return child(root.getRight(), value);
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
					
					NodeTree<T> last = child(root.getLeft(), value);
					Number number = (Number) last.getValue();
					if(numberParm.longValue() > number.longValue()) {
						NodeTree<T> node = new NodeTree<T>(value);
						node.setFather(last);
						last.setRight(node);
					}else {
						System.out.println(value);
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
	
	public NodeTree<T> searchNode(NodeTree<T> root, T value){
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
	
	private boolean isChildLeft(NodeTree<T> node) {
		return node.getLeft() != null ? true : false;
	}
	
	private boolean isChildRight(NodeTree<T> node) {
		return node.getRight() != null ? true : false;
	}
	
	
	private Number sucessor(T val) {
		Number number = (Number) val;
		return number.longValue() + 1;
	}
	
	private NodeTree<T> searchMin(NodeTree<T> node, T value){
		if(sucessor(value).longValue() == ((Number) value).longValue() && ((Number) node.getValue()).longValue() > sucessor(value).longValue()) {
			return node;
		}else {
			if(node.getLeft() != null) {
				return searchMin(node.getLeft(), value);
			}
			if(node.getRight() != null) {
				return searchMin(node.getRight(), value);
			}
		}
		return node;
	}
	
	public void remove(NodeTree<T> root, T value) {
		NodeTree<T> nodeTree = searchNode(root, value);
		
		if(nodeTree != null) { 
			 NodeTree<T> nodeFather = nodeTree.getFather();
			 if(nodeFather != null) { 
				 if(isChildLeft(nodeTree) && isChildRight(nodeTree)) {
					 
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
								  System.out.println(nodeFather.getValue());
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
