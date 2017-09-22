public class ClosestLeaf {
	public static int getClosestLeaf(Node n) {
		
		return 0;
	}

	public static void main(String[] args) {

	}
}


class Node {
	int data;
	Node left;
	Node right;
	Node parent;

	public Node(Node p) {
		parent = p;
	}

	public boolean isRoot() {	// is root if this node has no parent
		return parent == null;
	}
}