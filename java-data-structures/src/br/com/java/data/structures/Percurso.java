package br.com.java.data.structures;

public class Percurso<T extends Object> {
	private QueueDataStructure<NodeTree<T>> fila = new QueueDataStructure<NodeTree<T>>();
	
	private void visitor(NodeTree<T> nodeTree) {
		System.out.print(nodeTree.getValue() + " ");
	}
	
	public void nivel(NodeTree<T> root, TreeBinaryDataStructure<T> tree) {
		if(root != null) {
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
	}
	
	public void preOrdem(NodeTree<T> root) {
		if(root != null) {
			visitor(root);
			if(root.getLeft() != null) {
				preOrdem(root.getLeft());
			}
			if(root.getRight() != null) {
				preOrdem(root.getRight());
			}
		}
	}
	
	public void posOrdem(NodeTree<T> root) {
		if(root != null) {
			if(root.getLeft() != null) {
				posOrdem(root.getLeft());
			}
			if(root.getRight() != null) {
				posOrdem(root.getRight());
			}
			visitor(root);
		}
	}
	
	public void ordemSimetrica(NodeTree<T> root) {
		if(root != null) {
			if(root.getLeft() != null) {
				ordemSimetrica(root.getLeft());
			}
			visitor(root);
			if(root.getRight() != null) {
				ordemSimetrica(root.getRight());
			}
		}
	} 
}
