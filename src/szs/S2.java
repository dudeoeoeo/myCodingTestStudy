package szs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S2 {

    int[] answer = new int[2];
    boolean finish = false;
    List<Integer> dropped = new ArrayList<>();

    public int[] solution(int[][] orders) {

        int len = orders.length;

        int half = len % 2 == 0 ? len / 2 : len / 2 + 1;

        int cnt = 0;
        for (int i = 0; i < len; i++) {
            cnt++;
            int [] candidates = new int[len];

            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    if (dropped.contains(orders[j][k]) == false) {
                        candidates[orders[j][k]]++;
                        break;
                    }
                }
            }

            halfScore(candidates, half);
            if (finish) {
                answer[0] = cnt;
                break;
            }
            dropCandidate(candidates);
        }

        return answer;
    }

    public void halfScore(int [] candidates, int half) {

        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] >= half) {
                answer[1] = i;
                finish = true;
            }
        }
    }
    public void dropCandidate(int [] candidates) {
        int [] drop = {0, Integer.MAX_VALUE};

        for (int i = 0; i < candidates.length; i++) {
            if (dropped.contains(i))
                continue;
            if (candidates[i] < drop[1]) {
                drop[0] = i;
                drop[1] = candidates[i];
            }
        }
        dropped.add(drop[0]);
    }

    public static void main(String[] args) {
        S2 s2 = new S2();
//        int [][] orders = {{2, 3, 4, 0, 1}, {1, 4, 3, 2, 0}, {4, 1, 0, 2, 3}, {3, 2, 1, 4, 0}, {0, 3, 2, 1, 4}};
        int [][] orders = {{2, 1, 0, 3}, {3, 2, 0, 1}, {3, 0, 2, 1}, {2, 3, 0, 1}};
        System.out.println(Arrays.toString(s2.solution(orders)));
    }
}
