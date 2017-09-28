/*
Leetcode MEDIUM: https://leetcode.com/problems/rotate-list/description/
Rotate a linked list

This approach is O(n), two-pass
I couldn't think of a way to do this in one pass without a lot of drafting, so
I decided to use a two-pass approach. The first pass measures the length of the
linked list, which allows us to easily determine the locations of the three
ListNodes that must be noted and modified:
1. The NEW head (to be returned)
2. The NEW tail (to be pointed to NULL)
3. The OLD tail (to be pointed to the OLD head)

After the first pass, we calculate the locations of the above NEW ListNodes.
We pass the Linked List again and save the ListNodes that need to be modified.
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class RotateList {
    public static ListNode rotateRight(ListNode head, int k) {
        int len = getLength(head);      // O(n) time
        
        if (len == 0)
            return head;
        
        k %= len;
        
        if (k == 0)
            return head;
        
        int newHeadLoc = len - k;   // will always be >= 1
        int newTailLoc = newHeadLoc - 1;
        ListNode newTail = null;
        ListNode newHead = null;
        ListNode oldHead = head;
        
        for (int i = 0; i < len; i++) {     // won't go out of bounds because this was CALCULATED
            if (i == newTailLoc)
                newTail = head;
            else if (i == newHeadLoc)
                newHead = head;
            
            if (i != len - 1)
                head = head.next;       // keep head from going to null so we can edit head
        }
        
        // changes here:
        newTail.next = null;
        head.next = oldHead;
        
        return newHead;
    }
    
    private static int getLength(ListNode head) {
        int i = 0;
        
        while (head != null) {
            head = head.next;
            i++;
        }
        
        return i;
    }

    public static void main(String[] args) {
        ListNode head = null;
        ListNode current = null;
        
        for (int i = 0; i < args.length - 1; i++) {
            if (i == 0) {
                head = new ListNode(Integer.parseInt(args[i]));
                current = head;
            } else {
                current.next = new ListNode(Integer.parseInt(args[i]));
                current = current.next;
            }
        }

        ListNode newList = rotateRight(head, Integer.parseInt(args[args.length - 1]));

        while (newList != null) {
            System.out.print(newList.val + " ");
            newList = newList.next;
        }

        System.out.println();
    }
}