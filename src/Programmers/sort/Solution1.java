package Programmers.sort;

import java.util.Arrays;

// H-Index
public class Solution1 {
    public int solution(int[] citations) {
        Arrays.sort(citations);

        int idx = 1;
        int cnt = 0;

        for (int i = citations.length - 1; i >= 0; i--) {
            int now = citations[i];

            if (now < idx) return cnt;
            else idx++; cnt++;
        }

        return citations.length;
    }
}
