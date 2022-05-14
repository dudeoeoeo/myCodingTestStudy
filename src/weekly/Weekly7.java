package weekly;

import java.util.*;

public class Weekly7 {
    int[] answer;
    public int[] solution(int[] enter, int[] leave) {
        answer = new int[leave.length];

        Queue<Integer> queue = new LinkedList<>();

        int nextEnter = 0;
        int nextLeave = 0;
        int e_idx = 0;
        int l_idx = 0;

        while (e_idx < enter.length || l_idx < leave.length) {

            if(e_idx < enter.length)
                nextEnter = enter[e_idx];
            nextLeave = leave[l_idx];
            if(queue.contains(nextLeave)) { // 방에 있는 사람이 나갈 사람이 맞는지 확인
                queue.remove(nextLeave);
                answer[nextLeave - 1] += queue.size();
                Queue<Integer> q = queue;
                plusCheck(q);
                l_idx++;
            } else { // 방에 아직 다음 나갈 순서의 사람이 없다면
                queue.offer(nextEnter); // 처음 방에 입장한 사람 집어 넣기\
                e_idx++;
            }
        }
        return answer;
    }
    void plusCheck(Queue<Integer> q) {
        //System.out.println("plusCheck 호출");
        int size = q.size();
        for(int i = 0; i < size; i++) {
            int enter = q.poll();
            answer[enter - 1]++;
            q.offer(enter);
        }
    }

    public static void main(String[] args) {
        Weekly7 w7 = new Weekly7();
        int [] enter = {1, 4, 2, 3};
        int [] leave = {2, 1, 3, 4};
        System.out.println(Arrays.toString(w7.solution(enter, leave)));
    }
}
