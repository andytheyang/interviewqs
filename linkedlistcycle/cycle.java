// Finds cycle in linked list in O(n) time and constant memory
// basic solution would be to use a hashset, which would also use O(n) time but O(n) memory
public class cycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slowptr = head;
        ListNode fastptr = head.next;
        
        while (fastptr != null && fastptr.next != null) {
            if (slowptr == fastptr)
                return true;
                
            slowptr = slowptr.next;
            fastptr = fastptr.next.next;        // eventually, fast pointer will catch up and "lap" slow pointer
        }
        
        
        return false;       // if one pointer reaches null (the end), there must be no cycle
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}