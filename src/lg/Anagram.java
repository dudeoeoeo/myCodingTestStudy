package lg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Anagram {

    Set<String> set = new HashSet<>();

    public int solution(int[] arr) {
        Arrays.sort(arr);

        int [][] nums = new int[arr.length][10];

        int idx = 0;
        for (int now : arr) {
            String value = String.valueOf(now);

            for (char v : value.toCharArray()) {
                int index = Integer.valueOf(String.valueOf(v));
                nums[idx][index]++;
            }
            idx++;
        }

        groupCheck(nums);

        return set.size();
    }

    public void groupCheck(int [][] nums) {
        StringBuilder sb = new StringBuilder();
        for (int [] num : nums) {

            for (int i = 0; i < 10; i++) {
                int now = num[i];
                if (now > 0)
                    sb.append(i + "" + now);
            }
            set.add(sb.toString());
            sb.setLength(0);
        }
    }

    public static void main(String[] args) {
        Anagram l1 = new Anagram();
        int [] s = {112, 1201};
        l1.solution(s);
    }
}
