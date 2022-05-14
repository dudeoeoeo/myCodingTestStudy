package Programmers.heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

// 이중 우선순위 큐
public class Solution1 {
    public int[] solution(String[] operations) {
        Queue<Integer> order = new PriorityQueue<>();
        Queue<Integer> reverseOrder = new PriorityQueue<>(Collections.reverseOrder());

        for (String op : operations) {
            String [] oper = op.split(" ");
            int val = Integer.parseInt(oper[1]);
            if (oper[0].equals("I")) {
                reverseOrder.offer(val);
                order.offer(val);
            }
            else {
                if (!order.isEmpty()) {
                    if (val == 1)
                        order.remove(reverseOrder.poll());
                    else
                        reverseOrder.remove(order.poll());
                }
            }
        }

        return order.isEmpty() ? new int[]{0, 0} : new int[]{reverseOrder.peek(), order.peek()};
    }
}
