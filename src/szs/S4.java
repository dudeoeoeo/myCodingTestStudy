 package szs;

import java.util.*;

public class S4 {

    Map<Integer, ArrayList<Integer>> map = new HashMap<>();
    List<Integer> stack = new ArrayList<>();
    List<Integer> deleteNumList = new ArrayList<>();
    int N;

    public int[] solution(int n, int[][] queries) {
        N = n;

        for (int i = 0; i < n; i++) {
            map.put(i+1, new ArrayList<>());
        }

        // [a, b]
        // a > 1 && a <= n
        // b == -1 || (b >= 1 && b <= 1000000)
        // b >= 1 push
        // b == -1 pop
        for (int i = 0; i < queries.length; i++) {
            int [] query = queries[i];
            int stackNum = query[0];
            int active = query[1];

            // pop
            if (active == -1) {
                if (map.get(stackNum).size() == 0) {
                    deleteNumList.add(-1);
                    continue;
                }
                // center
                if (map.get(stackNum).size() == 1) {
                    popCenterStack(stackNum);
                } else if (map.get(stackNum).size() > 1) {
                    popStack(stackNum);
                }
                // pop 하고 stack 비었다면
                if (map.get(stackNum).size() <= 0) {
                    fillCenter(stackNum);
                }
            // push
            } else {
                if (map.get(stackNum).size() <= 0) {
                    fillAllStack(active);
                } else {
                    pushStack(stackNum, active);
                }
            }
        }

        int[] answer = new int[deleteNumList.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = deleteNumList.get(i);
        }

        return answer;
    }

    void fillCenter(int stackNum) {
        int cnt = 0;
        int stack = (stackNum + 1) % N;

        while (cnt < N) {
            if (stack == 0) ++stack;
            if (map.get(stack).size() == 0) {
                if (++stack == N) {
                    stack = N;
                    cnt++;
                    continue;
                }
                stack = stack % N;
                cnt++;
                continue;
            }
            int fillNum = map.get(stack).get(0);

            for (int i = 1; i <= N; i++) {
                System.out.println(map.get(stack).toString());
                if (i == stack) continue;
                map.get(i).add(0, fillNum);
            }
            return;
        }
    }

    void popStack(int stackNum) {
        int deleteNum = map.get(stackNum).get(map.get(stackNum).size() - 1);
        map.get(stackNum).remove(map.get(stackNum).size() - 1);

        deleteNumList.add(deleteNum);
    }

    void popCenterStack(int stackNum) {
        int deleteNum = map.get(stackNum).get(0);

        for (int i = 1; i <= N; i++) {
            map.get(i).remove(0);
        }
        deleteNumList.add(deleteNum);
    }

    void pushStack(int stackNum, int pushNum) {
        map.get(stackNum).add(pushNum);
    }

    void fillAllStack(int pushNum) {
        for (int i = 1; i <= N; i++) {
            map.get(i).add(0, pushNum);
        }
    }

    public static void main(String[] args) {
        S4 s4 = new S4();
        // 3 3 3 3
        // 3 2 , 3 , 3 , 3
        // 3 2 , 3 , 3 6 , 3
        // 3 2 , 3 , 3 , 3  -- > 6
        // 3 2 , 3 , 3 , 3 5
        // 2 , [] , [] , 5 -- > 3 => 5 2 , 5 , 5 , 5 fillNum
        // 2 , [] , [] , [] -- > 5 => 2 , 2 , 2 , 2 fillNum
        // 2 , 2 , 2 , 2 -- > 2 => [] , [] , [] , []

//        int [][] queries = {{1, 3}, {1, 2}, {3, 6}, {3, -1}, {4, 5}, {2, -1}, {3, -1}, {1, -1}};
        int [][] queries = {{1, -1}, {1, -1}, {3, -1}, {3, -1}, {4, -1}, {2, -1}, {3, -1}, {1, -1}};
        System.out.println(Arrays.toString(s4.solution(4, queries))); // 6 3 5 2
    }
}
