package snack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sn3 {

    Set<String> set = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    int cnt, sum, len;

    public int solution(int[] arr, int k, int t) {
        Arrays.sort(arr);
        cnt = k; sum = t; len = arr.length;

        insurance(arr, 0, 0, new StringBuilder(), 0);

        return set.size();
    }

    void insurance(int [] arr, int pos, int weight, StringBuilder candi, int plus) {
        if (plus >= cnt)
            check(weight, candi);
        if (pos >= len)
            return;

        insurance(arr, pos + 1, weight + arr[pos], candi.append(pos), plus + 1);
        candi.setLength(candi.length() - getLength(pos));
        insurance(arr, pos + 1, weight, candi, plus);
    }

    void check(int nowWeight, StringBuilder nowString) {
        if (nowWeight <= sum) {
            if (set.contains(nowString.toString()) == false)
                set.add(nowString.toString());
        }
    }

    int getLength(int now) {
        sb.setLength(0);
        sb.append(now);
        return sb.length();
    }

    public static void main(String[] args) {
        Sn3 sn3 = new Sn3();
        int [] arr = {2, 4, 6, 12, 30, 12, 43, 523, 123, 23, 67, 1234, 346};
        int k = 4, t = 2543;

        System.out.println(sn3.solution(arr, k, t));
    }
}
