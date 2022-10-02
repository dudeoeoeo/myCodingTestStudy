package dev;

import java.util.HashSet;
import java.util.Set;

public class D1 {

    Set<String> set = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    StringBuilder numSb = new StringBuilder();

    public String solution(String[] registered_list, String new_id) {
        for (String r : registered_list)
            set.add(r);

        if (set.contains(new_id) == false)
            return new_id;

        int idx = getIdx(new_id);

        if (idx > -1) {
            getNumber(idx, new_id);
        }

        int num = 1;

        if (numSb.length() > 0) {
            num = Integer.parseInt(numSb.toString());
        }

        recommendId(num);

        return sb.toString();
    }

    public void getNumber(int idx, String newId) {
        for (int i = idx; i < newId.length(); i++) {
            numSb.append(newId.charAt(i));
        }
    }

    public void recommendId(int num) {
        while (true) {
            if (set.contains(sb.toString() + ++num) == false) {
                sb.append(num);
                break;
            }
        }

    }

    public int getIdx(String nowId) {
        for (int i = 0; i < nowId.length(); i++) {
            char now = nowId.charAt(i);
            if (Character.isDigit(now) == false) {
                sb.append(now);
                continue;
            }
            return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        D1 d1 = new D1();
        String [] rr = {"card", "ace13"};
        String newId = "ace13";
        System.out.println(d1.solution(rr, newId));
    }
}
