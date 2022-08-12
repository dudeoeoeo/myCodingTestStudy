package Greedy;

import java.util.Arrays;

public class CrashBoat {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int len = people.length;
        boolean [] safe = new boolean[len];

        int answer = 0;
        int i = 0, reverse = len - 1;

        while (true) {
            if (safe[reverse] && safe[i]) break;
            if (i == reverse) {
                answer++;
                break;
            }

            int front = people[i], rear = people[reverse];

            if (front + rear <= limit) {
                safe[i] = true;
                safe[reverse] = true;
                answer++;
                reverse--;
                i++;
            } else {
                safe[reverse] = true;
                answer++;
                reverse--;
            }
        }

        return answer;
    }
}
