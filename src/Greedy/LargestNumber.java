package Greedy;

public class LargestNumber {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(number);
        StringBuilder ans = new StringBuilder();
        int len = number.length() - k;
        int pick = 1;

        while (true) {
            int loop = k <= 1 ? sb.length() : sb.length() - (len - pick++);
            int idx = 0;
            char max = 0;
            for (int i = 0; i < loop; i++) {
                char now = sb.charAt(i);
                if (max < now) {
                    idx = i; max = now;
                }
                if (max == 9) break;
            }
            ans.append(sb.charAt(idx));
            sb.delete(0, idx + 1);
            k--;
            if (ans.length() == len) return ans.toString();
            if (ans.length() + sb.length() == len) {
                ans.append(sb.toString());
                return ans.toString();
            }
        }
    }
}
