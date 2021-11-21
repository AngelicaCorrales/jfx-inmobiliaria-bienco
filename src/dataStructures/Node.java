package dataStructures;

public class Node<V extends Comparable<V>> implements Comparable<V>{
	
	private V value;
	private Node<V> left;
	private Node<V> right;
	private Node<V> parent;
	
	public Node(V source) {
		this.value = source;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Node<V> getLeft() {
		return left;
	}

	public void setLeft(Node<V> left) {
		this.left = left;
	}

	public Node<V> getRight() {
		return right;
	}

	public void setRight(Node<V> right) {
		this.right = right;
	}

	public Node<V> getParent() {
		return parent;
	}

	public void setParent(Node<V> parent) {
		this.parent = parent;
	}

	@Override
	public int compareTo(V val) {
		return value.compareTo(val);
	}
	
}
