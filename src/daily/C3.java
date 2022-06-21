package daily;

import java.util.HashMap;
import java.util.Map;

public class C3 {
    public int solution(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            if (map.containsKey(a))
                map.remove(a);
            else
                map.put(a, a);
        }

        int answer = 0;
        for (Integer key : map.keySet())
            answer = key;

        return answer;
    }

    public static void main(String[] args) {
        C3 c3 = new C3();
        int [] A = {9, 3, 9, 3, 9, 7, 9};
        System.out.println(c3.solution(A));
    }
}
