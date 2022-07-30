package KaKao;

import java.util.*;

public class Scoville {

    public int solution(int[] scoville, int K) {
        int answer = 0;
        int min = 0;
        int second = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int scov : scoville)
            pq.offer(scov);

        while (!pq.isEmpty()) {
            if(pq.peek() >= K)
                break;
            if(pq.size() <= 1)
                break;
            answer++;
            min = pq.poll();
            second = pq.poll();

            pq.add(min + (second * 2));
        }
        if(pq.isEmpty())
            return -1;
        else if(pq.size() == 1 && pq.peek() < K)
            return -1;

        while (!pq.isEmpty()) {
            if(pq.poll() >= K)
                break;
            answer++;
        }
        return answer;
    }

}
