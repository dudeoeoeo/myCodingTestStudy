package snack;

import java.util.Arrays;

public class Sn4 {

    boolean [] carried;
    int answer, W, cnt;

    public int solution(int M, int[] load) {
        Arrays.sort(load);
        W = M;
        if (load[load.length - 1] > M)
            return -1;

        carried = new boolean[load.length];

        int s = 0, c = 0;
        for (int i = 0; i < load.length; i++) {
            if (s + load[i] <= M) {
                s += load[i];
            } else {
                s = 0;
                c++;
            }
        }

        for (int i = 0; i < load.length; i++) {
            if (setBox(i, load))
                answer++;
            if (cnt == load.length) return answer;
        }

        return Math.min(answer, c);
    }

    public boolean setBox(int start, int [] load) {
        boolean reverse = false;
        int weight = 0, end = getEndNum();

        for (int i = start; i < load.length; i++) {
            if (weight >= W) break;
            if (reverse) {
                if (carried[end]) {
                    end--;
                    continue;
                }
                if (weight + load[end] <= W) {
                    carried[end] = true;
                    weight += load[end--];
                    reverse = true;
                    cnt++;
                    continue;
                }
                end--;
                if (end <= start) break;
            } else {
                if (carried[start]) continue;
                if (weight + load[start] <= W) {
                    carried[start] = true;
                    reverse = true;
                    weight += load[start++];
                    cnt++;
                } else {
                    break;
                }
            }
        }
        return weight <= 0 ? false : true;
    }

    public int getEndNum() {
        for (int i = carried.length - 1; i >= 0; i--) {
            if (carried[i]) continue;
            return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Sn4 sn4 = new Sn4();
        int M = 6;
        // 1, 1, 1, 5, 5, 5, 6, 6
        // 6 6 => 2
        // 5 1, 5 1, 5 1 => 3
        // 1, 1, 1, 1, 1, 2, 2, 2, 2, 2,  3, 3, 3, 3, 3,
        // 1, 2, 3 / 1, 2, 3 / 1, 2, 3 / 1, 2, 3 / 1, 2, 3 => 5
        // 2, 2, 2, 2, 4, 4,
        // 2 4 / 2 4 / 2 2 => 3    2 3 3 5 => 13
        int [] load = {1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 1, 2, 4, 5, 6, 1, 2, 4, 5, 2, 3, 1, 2, 3, 5, 1, 2, 6};
    }
}
