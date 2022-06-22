package daily;

import java.util.Arrays;

public class C5 {
    public int solution(int[] A) {
        if (A.length == 0) return 1;
        if (A.length < 2) return A[0] == 1 ? 2 : 1;

        Arrays.sort(A);

        int max = A[A.length - 1];

        for (int i = 1; i <= max; i++) {
            if (i != A[i - 1]) return i;
        }

        return max + 1;
    }
}
