package br.com.java.data.structures;

import br.com.java.data.structures.util.Search;
import br.com.java.data.structures.util.Util;

// Fonte: Autoria Propria
// Classe Arvoré Binaria (Estrutura de Dados Não-Linear) 
public class TreeBinaryDataStructure<T extends Object> implements DataStructures<T>{
	// atributos da classe 
	
	private NodeTree<T> root = new NodeTree<T>(); // criando o objeto NodeTree com referencia da raiz da arvore
	private Integer size = 0; // objeto Integer que será usada para informar o tamanho de 'nós' na arvore
	private NodeTree<T> nodeTreeAux = null;  // criando um objeto NodeTree auxiliar
	private StackDataStructure<T> stackDataStructure = new StackDataStructure<T>(); // criando um objeto Stack (Pilha) para ser usada como auxiliar
	private Util<T> util = new Util<T>(); // criando um objeto util para ser util em algumas partes do código
	
	// Construtor da classe
	public TreeBinaryDataStructure(T value) { // Recebe por parâmetro o valor da raiz 
		this.root.setValue(value); // seta o valor
		this.stackDataStructure.add(value); // inserir o valor na Stack (Pilha)
		size++; // incrementa
	}
	
	// Método privado
	// Método usado para consultar o nó a partir da raiz e retornar um valor que esteja na arvore menor ou maior para o valor que será passado por parametro
	// e assim será util para inserir na arvore
	private NodeTree<T> child(NodeTree<T> root, T value) { // Recebe por referencia o nó raiz e o valor que esteja a procura segundo a regra do método 
		// força a conversão do objeto em long, ou seja um cast
		long numberParm = ((Number) value).longValue();
		long numberRoot = ((Number) root.getValue()).longValue();
		
		// Abaxio começa as regras do método
		
		// Essa condição têm duas verdades que devem ser verificadas, a primeira é se o valor que esteja a procura e o valor do nó
		// Se o valor (numberParm) for menor que do nó (numberRoot) será verdadeiro, caso contrario será falso.
		// a segunda verificação será se o nó não tiver um filho a esquerda, caso as duas condições tiverem corretas será verdade e retornar o nó (root), 
		// senão o fluxo continua como mostra abaixo
		if((numberParm < numberRoot) && !isChildLeft(root)) {
			return root;
		}
		// Como é semelhante a condição acima têm duas verdades que devem ser verificadas, a primeira é se o valor que esteja a procura e o valor do nó
		// Se o valor (numberParm) for maior que do nó (numberRoot) será verdadeiro, caso contrario será falso.
		// a segunda verificação será se o nó não tiver um filho a direita, caso as duas condições tiverem corretas será verdade e retornar o nó (root), 
		// senão o fluxo continua como mostra abaixo
		if((numberParm > numberRoot) && !isChildRight(root)) {
			return root;
		}
		// Como é semelhante a condição acima têm duas verdades que devem ser verificadas, a primeira é se o valor que esteja a procura e o valor do nó
		// Se o valor (numberParm) for menor que do nó (numberRoot) será verdadeiro, caso contrario será falso.
		// a segunda verificação será se o nó tiver um filho a esquerda, caso as duas condições tiverem corretas será verdade e continua a busca pela subarvore a esquerda, 
		// senão o fluxo continua como mostra abaixo
		if((numberParm < numberRoot) && (root.getLeft() != null)) {
			return child(root.getLeft(), value); // função recursiva. faz o percurso nas subarvores a esquerda
		}
		// Como é semelhante a condição acima têm duas verdades que devem ser verificadas, a primeira é se o valor que esteja a procura e o valor do nó
		// Se o valor (numberParm) for maior que do nó (numberRoot) será verdadeiro, caso contrario será falso.
		// a segunda verificação será se o nó tiver um filho a direita, caso as duas condições tiverem corretas será verdade e continua a busca pela subarvore a direita, 
		if((numberParm > numberRoot) && (root.getRight() != null)) {
			return child(root.getRight(), value);// função recursiva. faz o percurso nas subarvores a direita
		}
		
		return root;
	}
	
	// Método public
	// Método usado para inserir valores na arvore binaria
	@Override
	public void add(T value) {
		size++; // incrementa o total de nós
		final String type = 
				value.getClass().getGenericSuperclass().getTypeName().substring(10,
						value.getClass().getGenericSuperclass().getTypeName().length()); // retorna uma string com que contenha o valor do tipo do objeto passado por parametro
		if(type.equals("Number")) { // Se for do tipo Number, segue o fluxo abaixo
			stackDataStructure.add(value); // Inserir na Stack(Pilha)
			Number numberParm = (Number) value; // converte o valor
			Number numberRoot = (Number) root.getValue(); // converte o valor
			
			// Abaixo será inserido o valor na arvore binaria
			
			
			// Essa condição têm duas verdades que devem ser verificadas, a primeira é se a raiz não tem filho a esquerda
			// e a segunda é se o valor(numberParm) é menor que da raiz(numberRoot). Caso ambas serem verdades o nó será inserido na arvore a esquerda
			if((isEmptyLeft()) && (numberParm.longValue() < numberRoot.longValue())) {
				NodeTree<T> node = new NodeTree<T>(value); // Instancia um novo nó do tipo NodeTree
				node.setFather(root); // seta a raiz como o pai do nó
				root.setLeft(node); // seta o nó como filho a esquerda da raiz (e será uma raiz da subarvore a esquerda...)
			}
			// Se a condição acima não foi verdadeira, será verificada a proxima condição, como mostra abaixo
			// Senão se a raiz não tem filho a direita e o valor(numberParm) for maior que a raiz (numberRoot). 
			// Caso ambas serem verdades o nó será inserido na arvore a direita
			else if((isEmptyRight()) && (numberParm.floatValue() > numberRoot.longValue())) {
				NodeTree<T> node = new NodeTree<T>(value); // Instancia um novo nó do tipo NodeTree
				node.setFather(root); // seta a raiz como o pai do nó
				root.setRight(node); // seta o nó como filho a direita da raiz (e será uma raiz da subarvore a direita...)
			}else {
				// Senão
				// Se a condição acima não foi verdadeira, será verificada a proxima condição, como mostra abaixo
				// Senão a raiz já tem filho a direita e a esquerda. Se o valor(numberParm) for menor que a raiz (numberRoot). 
				// Caso seja verdade, será realizado uma busca
				if(numberParm.longValue() < numberRoot.longValue()) {
					NodeTree<T> nodeChildRetor = child(root.getLeft(), value); // retorna da busca
					if(nodeChildRetor != null) { // Se for diferente de null
						Number number = (Number) nodeChildRetor.getValue(); // converte o valor
						if(numberParm.longValue() > number.longValue()) { // Se numberParm é maior que o number(valor retornado da funcao) será inserido a direita do nó (nodeChildRetor)
							NodeTree<T> node = new NodeTree<T>(value); // instancia o novo nó
							node.setFather(nodeChildRetor); // seta o pai no nó (node)
							nodeChildRetor.setRight(node); // seta o filho a direita
						}else { // Senão
							NodeTree<T> node = new NodeTree<T>(value); // instancia o novo nó
							node.setFather(nodeChildRetor); // seta o pai no nó (node)
							nodeChildRetor.setLeft(node); // seta o filho a esquerda
						}
					}
					 
				} // Senão
				else {
					NodeTree<T> nodeChildRetor = child(root.getRight(), value); // retorna da busca
					if(nodeChildRetor != null) { // Se for diferente de null
						// Se a condição acima não foi verdadeira, será verificada a proxima condição, como mostra abaixo
						// Senão a raiz já tem filho a direita e a esquerda. Se o valor(numberParm) for menor que a raiz (numberRoot). 
						// Caso seja verdade, será realizado uma busca
						Number number = (Number) nodeChildRetor.getValue();// converte o valor
						if(numberParm.longValue() > number.longValue()) {// Se numberParm é maior que o number(valor retornado da funcao) será inserido a direita do nó (nodeChildRetor)
							NodeTree<T> node = new NodeTree<T>(value);// instancia o novo nó
							node.setFather(nodeChildRetor);// seta o pai no nó (node)
							nodeChildRetor.setRight(node);// seta o filho a direita
						}else {// senao
							NodeTree<T> node = new NodeTree<T>(value);// instancia o novo nó
							node.setFather(nodeChildRetor);// seta o pai no nó (node)
							nodeChildRetor.setLeft(node);// seta o filho a esquerda
						}
					}
				}
			}
		}
	}
	// Método public
	// Método usado para retorna a raiz da arvore binaria
	public NodeTree<T> getRoot(){
		return root;
	}
	// Método public
	// Método sem uso para a estrutura da Arvore Binaria
	@Override
	public void remove() throws Exception {
		throw new Exception("unused method"); 
	}
	// Método public
	// Método usado para consultar um nó, caso ele exista, deve retorna-lo
	public NodeTree<T> searchNode(NodeTree<T> root, T value){ // Recebe por referencia a raiz e por parametro o valor
		Number numberParm = (Number) value; // converte o valor
		Number numberRoot = (Number) root.getValue(); // converte o valor
		if(root.equals(this.root)) { // Se a raiz por referencia é a raiz da arvore, se for verdade segue o fluxo dentro da condicao, senão continua o fluxo do método 
			if(numberParm.longValue() > numberRoot.longValue()) { // Se o numberParm (valor do parametro) é maior que o numberRoot(valor da raiz)
				root = root.getRight(); // a referencia (root) recebe a referencia do filho a direita
				nodeTreeAux = root; // objeto recebe a referencia do root(subarvore a direita)
			}else if(numberParm.longValue() < numberRoot.longValue()){ // senão se o numberParm (valor do parametro) é menor que o numberRoot(valor da raiz)
				root = root.getLeft(); // a referencia (root) recebe a referencia do filho a esquerda
				nodeTreeAux = root; // objeto recebe a referencia do root(subarvore a esquerda)
			}else { // senão, caso a raiz seja uma folha
				return root; 
			}
		}
		// Se o objeto nodeTreeAux tiver o valor igual ao value do parametro, retorna o nó com esse valor
		if(util.parseNumberLong(nodeTreeAux.getValue()) == util.parseNumberLong(value)) {
			return nodeTreeAux;
		}else {// Senão
			// se a referencia (root) tiver um filho a esquerda e o valor do objeto nodeTreeAux não for igual ao valor do parametro, deve continuar o percurso na arvore
			if(root.getLeft() != null && util.parseNumberLong(nodeTreeAux.getValue()) != util.parseNumberLong(value)) {
				nodeTreeAux = root.getLeft(); // recebe a referencia da subarvore esquerda
				searchNode(root.getLeft(), value); // percurso para a subarvore esquerda
			}
			// se a referencia (root) tiver um filho a direita e o valor do objeto nodeTreeAux não for igual ao valor do parametro, deve continuar o percurso na arvore
			if(root.getRight() != null && util.parseNumberLong(nodeTreeAux.getValue()) != util.parseNumberLong(value)) {
				nodeTreeAux = root.getRight(); // recebe a referencia da subarvore direita
				searchNode(root.getRight(), value); // percurso para a subarvore direita
			}  
		} 
		
		return nodeTreeAux;
	}
	
	// Método private
	// Método usado para verificar se o nó por referencia tem filho a esquerda
	private boolean isChildLeft(NodeTree<T> node) {
		return node.getLeft() != null ? true : false; // se tiver retorna verdadeiro (true), senão retorna falso (false)
	}
	// Método private
	// Método usado para verificar se o nó por referencia tem filho a direita
	private boolean isChildRight(NodeTree<T> node) {
		return node.getRight() != null ? true : false;// se tiver retorna verdadeiro (true), senão retorna falso (false)
	}
	// Método private
	// Método usado para retornar o nó que tenha um valor menor que o nó que será removido da arvore
	// Este método é somente usado para as subarvores que tenham filhos (esquerda e direita)
	private NodeTree<T> searchMin(NodeTree<T> root, T value){ // Recebe por referencia a raiz e por parametro o valor
		Search<T> search = new Search<T>(stackDataStructure.extractValues()); // Objeto usado para ser util no código abaixo
		Number numberParm = (Number) value; // converte o valor
		Number numberRoot = (Number) root.getValue(); // converte o valor
		if(root.equals(this.root)) { // se a referencia do root é a referencia da raiz, retorna verdadeiro
			NodeTree<T> nodeSearch = searchNode(root, value); // faz a consulta pelo valor (value)
			if(!isChildLeft(nodeSearch) && !isChildRight(nodeSearch)) { // se esse nó é folha, retorna null
				return null;
			} 
			// Se numberParm(value) é maior que o numberRoot(valor da referencia do root)
			if(numberParm.longValue() > numberRoot.longValue()) {
				root = root.getRight();// a referencia (root) recebe a referencia do filho a direita
				nodeTreeAux = root; // objeto recebe a referencia do root(subarvore a direita)
			}else if(numberParm.longValue() < numberRoot.longValue()){
				root = root.getLeft();// a referencia (root) recebe a referencia do filho a esquerda
				nodeTreeAux = root;// objeto recebe a referencia do root(subarvore a esquerda)
			}
		}
		// retorna o valor anterior do nó que será removido
		long seach = search.searchForPreviousValue(value) != null ? ((Number) search.searchForPreviousValue(value)).longValue() : -1;
		if(util.parseNumberLong(nodeTreeAux.getValue()) == seach) { // se existe esse valor retorna o nó que será trocado
			return nodeTreeAux;
		}
		// se a referencia (root) tiver um filho a esquerda e o valor(search) não for igual ao valor do nodeTreeAux, deve continuar o percurso na arvore
		if(root.getLeft() != null && util.parseNumberLong(nodeTreeAux.getValue()) != seach) {
			nodeTreeAux = root.getLeft();// recebe a referencia da subarvore esquerda
			searchMin(root.getLeft(), value);// percurso para a subarvore esquerda
		}
		// se a referencia (root) tiver um filho a direita e o valor(search) não for igual ao valor do nodeTreeAux, deve continuar o percurso na arvore
		if(root.getRight() != null && util.parseNumberLong(nodeTreeAux.getValue()) != seach) {
			nodeTreeAux = root.getRight();// recebe a referencia da subarvore direita
			searchMin(root.getRight(), value);// percurso para a subarvore direita
		}  
		
		return nodeTreeAux;
	}
	// Método public
	// Método usado para retornar todos os valores da arvore
	public T[] extractValues() {
		// retorno dos valores a partir da pilha usada como auxiliar
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
