public class ll2 {		// returns 2nd from end of linkedlist
	public static void main(String args[]) {
		ListNode head = new ListNode(0);
		// head.next = new ListNode(1);
		// head.next.next = new ListNode(2);
		// head.next.next.next = new ListNode(3);

		System.out.println(get2nd(head).data);
	}

	public static ListNode get2nd(ListNode head) {
		ListNode prev = null;

		while (head != null && head.next != null) {
			prev = head;
			head = head.next;
		}

		return prev;
	}
}

class ListNode {
	ListNode next;
	int data;

	public ListNode(int data) {
		next = null;
		this.data = data;
	}
}