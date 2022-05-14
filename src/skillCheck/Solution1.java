package skillCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
    public int[] solution(int n, int s) {
        int[] answer = {};
        if(n * 1 > s) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }

        answer = new int[n];
        int division = s / n;
        int rest = s % n;
        Arrays.fill(answer, division);
        int len = answer.length - 1;
        while (rest > 0) {
            answer[len]++;
            rest -= 1;
            len -= 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 2, s = 9;
        Solution1 s1 = new Solution1();
        System.out.println(Arrays.toString(s1.solution(n,s)));
    }
}
