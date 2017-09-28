/*
Leetcode MEDIUM: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
Remove duplicates from sorted linked list

This approach is O(n) with O(1) space
I used a fake head in order to allow scanning ahead of the current element. 
Because we need to COMPLETELY REMOVE duplicates, not just remove the following
duplicate values (which would be a lot easier), we must scan elements for
duplicates AFTER the current element being examined. By using a fakehead, we can
easily apply the algorithm work successfully at the start of the list. This
algorithm works as follows:
1. Make fake head to point to current head
2. loop:
3. scan for duplicates starting from node ahead of current node
4. go to step 2 until not a duplicate or null
5. if duplicates were found, adjust current node to point to after the last
duplicate
6. if not, advance current node to next node
7. repeat until current node at NULL
*/

public class RemoveDuplicates {
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        
        ListNode current = fakeHead;
        
        while (current.next != null) {
            ListNode scan = current.next;
            int val = scan.val;
            int len = 0;
            
            while (scan != null && scan.val == val) {
                scan = scan.next;
                len++;
            }
            
            if (len == 1)           // if there was no duplicate to remove
                current = current.next;
            else
                current.next = scan;
        }
        
        return fakeHead.next;
    }

    public static void main(String[] args) {
        ListNode head = null;
        ListNode current = null;
        
        for (int i = 0; i < args.length; i++) {
            if (i == 0) {
                head = new ListNode(Integer.parseInt(args[i]));
                current = head;
            } else {
                current.next = new ListNode(Integer.parseInt(args[i]));
                current = current.next;
            }
        }

        ListNode newList = deleteDuplicates(head);

        while (newList != null) {
            System.out.print(newList.val + " ");
            newList = newList.next;
        }

        System.out.println();
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}