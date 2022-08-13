package exam;

import java.util.Arrays;

public class Solution1 {
    int hour = 0;
    int solution(int [] v, int a, int b) {
        int [] hours = new int[v.length];

        Arrays.sort(v);
        for (int i = 0; i < v.length; i++) {
            hours[i] = v[i] / a;
        }

        while (check(v, a, b)) {
            Arrays.sort(v);
            hour++;

            for (int i = 0; i < v.length - 1; i++) {
                v[i] -= b;
            }
            v[v.length - 1] -= a;
        }
        return hour;
    }
    boolean check(int [] v, int max, int min) {
        Arrays.sort(v);
        if (v[v.length - 1] < max) return false;
        for (int i = 0; i < v.length - 1; i++) {
            if (v[i] < min) return false;
        }
        return true;
    }

}
