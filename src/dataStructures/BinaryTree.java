package dataStructures;

public class BinaryTree <V extends Comparable<V>>{

	private Node<V> root;
	
	public BinaryTree(V source) {
		root = new Node<V>(source);
	}
	
	public Node<V> getRoot() {
		return root;
	}
	
	public void insertNode(V vertex) {
		Node<V> n = new Node<V>(vertex);
		if(root==null) {
			root = n;
		}else {
			insertNodeRecursive(root, n);
		}
	}

	private void insertNodeRecursive(Node<V> current, Node<V> newNode) {
		if(newNode.compareTo(current.getValue())<0){
			if(current.getLeft()==null){
				current.setLeft(newNode);
				newNode.setParent(current);
			}else{
				insertNodeRecursive(current.getLeft(),newNode);
			}
		}else{
			if(current.getRight()==null){
				current.setRight(newNode);
				newNode.setParent(current);
			}else{
				insertNodeRecursive(current.getRight(),newNode);
			}	
		}
	}
}

