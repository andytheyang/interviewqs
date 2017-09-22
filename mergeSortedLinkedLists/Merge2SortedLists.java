// Merges two sorted linked lists. O(max(l1 size, l2 size)) runtime, O(l1 size + l2 size) memory (obviously)
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Merge2SortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode(0);      // temporary listnode for first element
        ListNode cur = ret;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            
            cur = cur.next;
        }
        
        if (l1 == null)
            cur.next = l2;
        else
            cur.next = l1;
            
        return ret.next;
        
    }
}