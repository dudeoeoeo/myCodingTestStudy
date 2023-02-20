package KaKaoMobility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class S2 {

    boolean [] visited;
    int ans = 0;
    Map<Integer, ArrayList<Integer>> rootMap = new HashMap<>();
    Map<Integer, ArrayList<Integer>> parentsMap = new HashMap<>();

    public int solution(int[] T, int[] A) {
        visited = new boolean[T.length];

        for (int i = 0; i < T.length; i++) {
            rootMap.put(i, new ArrayList<>());
            parentsMap.put(i, new ArrayList<>());
        }

        for (int i = 1; i < T.length; i++) {
            int t = T[i];
            if (rootMap.get(t).contains(i) == false)
                rootMap.get(t).add(i);
            if (parentsMap.get(i).contains(t) == false)
                parentsMap.get(i).add(t);
        }

        for (int a : A)
            dfs(a);

        return ans;
    }

    void dfs(int now) {
        if (visited[now]) return;
        visited[now] = true;
        ans++;

        for (int i = 0; i < parentsMap.get(now).size(); i++) {
            dfs(parentsMap.get(now).get(i));
        }
    }

    public static void main(String[] args) {
        S2 s2 = new S2();
        int [] T = {0, 3, 0, 0, 5, 0, 5}, A = {4, 2, 6, 1, 0};
        System.out.println(s2.solution(T, A));
    }
}
