package co;

public class T1 {

    public int[] solution(int N, int[] A) {
        int [] ans = new int[N];
        int lastMax = 0;
        int nowMax = 0;

        for (int now : A) {
            if (N < now) {
                lastMax = nowMax;
                continue;
            }
            if (ans[now - 1] < lastMax)
                ans[now - 1] = lastMax + 1;
            else
                ++ans[now - 1];
            nowMax = Math.max(nowMax, ans[now - 1]);
        }

        for (int i = 0; i < N; i++) {
            if (ans[i] < lastMax)
                ans[i] = lastMax;
        }

        return ans;
    }

}
