package daily;

import java.util.*;

public class C9 {
    public int solution(String S) {
        // write your code in Java SE 8
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');

        for (char s : S.toCharArray()) {
            if (s == '{' || s == '[' || s == '(')
                stack.push(s);
            else {
                if (stack.isEmpty() || map.get(stack.pop()) != s) return 0;
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }
}
