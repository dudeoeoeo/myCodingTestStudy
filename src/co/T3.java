package co;

public class T3 {
    public int solution(int K, int[] A) {
        int cnt = 0, idx = 0;

        while (idx < A.length) {
            if (A[idx] >= K) {
                cnt++; idx++;
                continue;
            }
            int now = 0;
            for (int i = idx; i < A.length; i++) {
                now += A[i];
                if (now >= K) {
                    cnt++;
                    idx = i;
                    break;
                }
            }
            if (cnt <= 0) return cnt;
            idx++;
        }

        return cnt;
    }
}
