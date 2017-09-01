/*
Level Order BST traversal
Leetcode MEDIUM: https://leetcode.com/problems/binary-tree-level-order-traversal/description/

This question is asked by facebook. Uses a FIFO structure (Queue/LinkedList) in
order to mark which nodes should be visited next.
*/

import java.util.Queue;
import java.util.LinkedList;

public class LevelOrder {
	private static Queue<Node> q;

	public static void printTraversal(Node root) {
		q = new LinkedList<>();
		q.add(root);
		Node current;

		while ((current = q.poll()) != null) {
			if (current.left != null)
				q.add(current.left);

			if (current.right != null)
				q.add(current.right);

			System.out.print(current.value + " ");
		}

		System.out.println();
	}

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		Node n10 = new Node(10);
		Node n11 = new Node(11);
		Node n13 = new Node(13);
		Node n14 = new Node(14);
		Node n15 = new Node(15);
		Node n18 = new Node(18);
		Node n20 = new Node(20);

		n10.left = n5;
		n10.right = n15;
		n5.left = n1;
		n5.right = n7;
		n7.left = n6;
		n7.right = n8;
		n15.left = n13;
		n15.right = n18;
		n13.left = n11;
		n13.right = n14;
		n18.right = n20;

		printTraversal(n10);
	}
}

class Node {
	Node left;
	Node right;
	int value;

	public Node(int val) {
		value = val;
		left = null;
		right = null;
	}
}