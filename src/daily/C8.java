package daily;

import java.util.*;

public class C8 {

    public int solution(int K, int[] C, int[] D) {

        Map<Integer, Integer> cleanSocks = new HashMap<>();
        int cleanNum = 0;
        for (int i : C) {
            if (cleanSocks.containsKey(i)) {
                ++cleanNum;
                --K;
                cleanSocks.remove(i);
            } else {
                cleanSocks.put(i, 1);
            }
        }
        if (K == 0) return cleanNum;

        List<Integer> dirtySocks = new ArrayList<>();
        // 이미 한 짝이 clean 상태인 양말 먼저 걷어내기
        for (int i : D) {
            if (cleanSocks.containsKey(i)) {
                ++cleanNum;
                --K;
                cleanSocks.remove(i);
            } else {
                dirtySocks.add(i);
            }
            if (K == 0) return cleanNum;
        }

        // 현재 여기는 더 이상 clean 상태인 양말이 없거나 빨래물과 일치하는 clean 양말이 없으므로 최대한 pair 를 맞춰서 빨래
        Collections.sort(dirtySocks);
        for (int i = 0; i < dirtySocks.size() - 1; i++) {
            if (dirtySocks.get(i) == dirtySocks.get(i + 1) && K >= 2) {
                ++cleanNum;
                K -= 2;
                ++i;
            }
            if (K == 0) return cleanNum;
        }

        return cleanNum;
    }

    public static void main(String[] args) {
        int K = 2;
        int [] C = {1, 2, 1, 1};
        int [] D = {1, 4, 3, 2, 4};
        C8 c8 = new C8();
        System.out.println(c8.solution(K, C, D));
    }
}
