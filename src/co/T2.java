package co;

public class T2 {
    public int solution(int[] A) {
        int idx = 0;
        double minVal = (A[0] + A[1]) / 2.0;

        for (int i = 2; i < A.length; i++) {
            double avg = (A[i - 2] + A[i - 1] + A[i]) / 3.0;
            if (minVal > avg) {
                idx = i - 2;
                minVal = avg;
            }

            avg = (A[i - 1] + A[i]) / 2.0;
            if (minVal > avg) {
                idx = i - 1;
                minVal = avg;
            }
        }

        return idx;
    }
}
