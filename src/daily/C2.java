package daily;

public class C2 {
    public int[] solution(int[] A, int K) {
        int len = A.length;
        int [] answer = new int[len];

        for (int i = 0; i < len; i++) {
            int rotation = (K + i) % len;
            answer[rotation] = A[i];
        }
        return answer;
    }
}
