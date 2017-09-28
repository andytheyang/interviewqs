/*
Leetcode HARD: https://leetcode.com/problems/merge-k-sorted-lists/description/
Merge K Sorted Linked Lists

This problem is similar to merge 2 sorted lists, but instead of just comparing
two ListNodes every time we have to maintain some sort of sorted data structure.
This structure will contain the heads of all sorted linked lists, and when each
head is appended to the end of our final list then this structure will add a new
head. I initially chose to use a TreeMap<Integer, ListNode> for this
implementation, as it offers O(log(n)) insert, remove, and findMin. However,
a Binary Min Heap offers average O(1) insert and worst case O(1) removeMin, 
which improves it vastly over a TreeMap. The only real challenge here was 
figuring out syntax for anonymous class declarations, edge cases aren't too
difficult.
Runtime for this entire function is (initialization + process):
Average: O(m + n)
Worst: O(mlog(m) + nlog(n))
*/

import java.util.PriorityQueue;
import java.util.Comparator;


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            public int compare(ListNode first, ListNode second) {
                return first.val - second.val;
            }
        });
        
        // initialize the queue
        for (ListNode l : lists)    // O(m)
            if (l != null)
                pq.add(l);          // Average O(1), Worst O(log(n)). This is better than tree which offers always O(log(n))
        
        ListNode head = null;
        ListNode prev = null;
        
        while (!pq.isEmpty()) {        // while map contains elements
            ListNode current = pq.poll(); // get and remove this key to introduce room for new
            
            if (prev == null)       // if first element
                head = current;         // this is the first element
            else
                prev.next = current;    // store current
            
            prev = current;
            if (current.next != null)
                pq.add(current.next);
        }
        
        return head;
    }
}