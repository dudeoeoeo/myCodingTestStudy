package daily;

import java.util.Stack;

public class C10 {
    public int solution(int[] A, int[] B) {
        if (A.length == 1) return 1;
        Stack<Integer> stack = new Stack<>();

        // 0 up 1 down
        int cnt = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            if (B[i] == 0) stack.push(A[i]);
            else {
                while (stack.isEmpty() == false) {
                    if (stack.peek() > A[i]) break;
                    else stack.pop();
                }
            }
            if (stack.isEmpty() == true) cnt++;
        }
        return stack.size() + cnt;
    }
}
