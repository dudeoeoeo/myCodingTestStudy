package daily;

public class C11 {
    public int solution(int[] A) {
        if (A.length == 2) return Math.abs(A[0] - A[1]);

        int val = 0;
        for (int a : A)
            val += a;

        int P = 0;
        int answer = Integer.MAX_VALUE;

        for (int a : A) {
            P += a;
            int temp = Math.abs(val - P);
            answer = Math.min(answer, Math.abs(P - temp));
        }
        return answer;
    }
}
