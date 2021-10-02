package KaKao;

import java.util.*;

public class StringPrime {

    List<Integer> list = new ArrayList<>();
    int answer = 0;
    boolean [] used = new boolean[7];

    public int solution(String numbers) {
        if (numbers.length() == 1) return 1;

        String[] nums = numbers.split("");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numbers.length(); i++) {
            rec(numbers, "", i + 1);
        }
        System.out.println(list.toString());
        for (int i = 0; i < list.size(); i++) {
            getPrime(list.get(i));
        }

        return answer;
    }

    public void getPrime(int now) {
        if(now <= 1) return;
        for (int i = 2; i < Math.sqrt(now); i++) {
            if(now % i == 0) return;
        }
        answer++;
    }
    public void rec(String numbers, String num, int pos) {
        if(pos >= numbers.length()) {
            if(!num.equals("") && !list.contains(Integer.parseInt(num)))
                list.add(Integer.parseInt(num));
            return;
        }

        if(!num.equals("") && !list.contains(Integer.parseInt(num)))
            list.add(Integer.parseInt(num));

        for(int i = 0; i < numbers.length(); i++) {
            if(!used[i]) {
                used[i] = true;
                num += numbers.charAt(i);
                rec(numbers, num, pos);
                used[i] = false;
                num = num.substring(0, num.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String numbers = "180018";
        StringPrime sp = new StringPrime();
        System.out.println(sp.solution(numbers));
    }
}
