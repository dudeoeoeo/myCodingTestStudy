package KaKaoMobility;

public class S3 {

    public int solution(int[] A) {
        if (A.length <= 2) return 1;

        int ans = 0, idx = 2, past = 2, max = 2, even = A[0], odd = A[1];

        // index 편의상 0 = even , 1 = odd
        while (idx < A.length) {
            System.out.printf("\n even: %d, odd: %d, A[%d]: %d \n", even, odd, idx, A[idx]);
            if (idx % 2 == 0) {
                // 기존 짝수와 같다면
                if (even == A[idx]) {
                    if (++max > 2 && ans < max) {
                        ans = max;
                    }
                    // 기존 짝수와 같지 않다면 max 초기화 및 현재 idx - 1 부터 시작
                } else {
                    even = A[idx];
                    --idx; max = 0;
                    continue;
                }
                // 홀수
            } else {
                if (odd == A[idx]) {
                    if (++max > 2 && ans < max) {
                        ans = max;
                    }
                } else {
                    odd = A[idx];
                    --idx; max = 0;
                    continue;
                }
            }
            idx++;
        }
        return ans;
    }

    public static void main(String[] args) {
        S3 s3 = new S3();
        int [] A = {7, 4, -2, 4, -2, -9};
        System.out.println(s3.solution(A));
    }
}
