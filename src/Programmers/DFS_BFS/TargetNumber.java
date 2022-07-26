package Programmers.DFS_BFS;

import java.util.Queue;
import java.util.LinkedList;

public class TargetNumber {
    class Pair {
        int val;
        int idx;
        Pair (int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int solution(int[] numbers, int target) {
        int answer = 0;
        Queue<Pair> p_queue = new LinkedList<>();
        p_queue.offer(new Pair(numbers[0], 0));
        p_queue.offer(new Pair(-numbers[0], 0));

        while (!p_queue.isEmpty()) {

            Pair p = p_queue.poll();
            if(p.idx == numbers.length-1) {
                if(p.val == target)
                    answer += 1;
                continue;
            }
            int val_plus = p.val + numbers[p.idx+1];
            int val_minus = p.val - numbers[p.idx+1];
            p_queue.offer(new Pair(val_plus, p.idx+1));
            p_queue.offer(new Pair(val_minus, p.idx+1));
        }
        return answer;
    }
}
