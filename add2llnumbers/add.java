// Adds two numbers whose digits are stored in two linked lists in reverse order
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class add {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        int l1val = 0;
        int l2val = 0;
        int sum;
        ListNode resultHead = new ListNode(0);      // dummy listnode
        ListNode resultCurrent = resultHead;
        
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                l1val = l1.val;
                l1 = l1.next;
            } else
                l1val = 0;
                
            if (l2 != null) {
                l2val = l2.val;
                l2 = l2.next;
            } else
                l2val = 0;
                
            sum = l1val + l2val + carry;
                
            resultCurrent.next = new ListNode(sum % 10);
            resultCurrent = resultCurrent.next;
            carry = sum / 10;
        }
        
        if (carry != 0)
            resultCurrent.next = new ListNode(carry);
        
        resultCurrent = resultHead.next;
        resultHead = null;          // make it easy on the garbage collector
        return resultCurrent;
    }
}