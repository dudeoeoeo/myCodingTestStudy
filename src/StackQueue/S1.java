package StackQueue;

import java.util.*;

public class S1 {
    public int[] solution(int[] progresses, int[] speeds) {
        int length = progresses.length;
        int [] answer = new int[length];

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            int progress = progresses[i];
            int speed = speeds[i];
            int num = 0;
            while (progress < 100) {
                progress += speed;
                ++num;
            }
            answer[i] = num;
        }
        int total = 0;
        int passNum = 1;
        int idx = 0;

        for (int i = 0; idx < length; i++) {
            int now = answer[idx];

            for (int j = idx + 1; j < length; j++) {
                idx++;
                int next = answer[j];
                if (now < next) break;
                passNum++;
            }
            total += passNum;
            list.add(passNum);
            passNum = 1;
            if (total >= length) break;
        }

        return list.stream().mapToInt(e -> e).toArray();
    }
}
