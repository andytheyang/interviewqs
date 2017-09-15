/*
Leetcode MEDIUM: https://leetcode.com/problems/task-scheduler/description/
Facebook interview question

This has O(time) runtime and O(n) space (n being the time interval)

We don't need to keep track of task names in this program because the only thing
that really affects runtime is the effect of each task (its frequency). The
theory to this method is that we will loop through the entire heap of 
frequencies, and loop through each cooldown interval, adding as many tasks as we
can during that interval (tasks that are not on cooldown). On the next
iteration, we can dump all those cooldown tasks back in the original queue 
because, since they can be ordered any way in the next block, there will be an
arrangement of them that will result in them all running in a valid state.
We run this loop until there are no more tasks on and off cooldown, and return
the final number of cycles passed.
*/

import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

class TaskSchedule {
    public static int leastInterval(char[] tasks, int n) {
        int[] numTasks = new int[26];       // keep track of count of each task
        
        for (char c : tasks)            // O(n)
            numTasks[c - 'A']++;        // increase count of this character
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(26, Collections.reverseOrder());  // make this a max heap. we want to get max occurances
        
        for (int num : numTasks)            // Heapify our values
            if (num > 0)
                pq.add(num);                // O(logn) insertion
        
        int totalTime = 0;
        
        while (!pq.isEmpty()) {
            List<Integer> onCoolDown = new LinkedList<>();
            Integer current;

            // for each set of cooldowns
            for (int i = 0; i <= n; i++) {  // makes the loop O(time)

                /* it is possible for pq to poll() an empty if all the current
                 * tasks are on cooldown but we have not finished considering 
                 * the current time block
                 */
                if ((current = pq.poll()) != null && current > 1)       // take the most frequent remaining task, and execute it
                    onCoolDown.add(current - 1);            // only store current - 1
                
                totalTime++;
                
                /*
                 * If there are no more tasks to schedule, there is no need to
                 * continue counting time
                 */
                if (pq.isEmpty() && onCoolDown.isEmpty())
                    break;
            }
            
            for (Integer i : onCoolDown)
                pq.add(i);
        }
        
        return totalTime;
    }

    public static void main(String[] args) {
        char[] tasks = new char[args.length - 1];

        for (int i = 0; i < tasks.length; i++)
            tasks[i] = args[i].charAt(0);

        int n = Integer.parseInt(args[args.length - 1]);

        System.out.println(leastInterval(tasks, n));
    }
}