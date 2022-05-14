package Anipointer;

public class Solution1 {
    public int solution(int n, int a, int b) {
        int answer = 0;

        while (true) {

            answer++;

            if(a == b)
                break;

            if(check(a, b))
                break;

            if(a % 2 != 0)
                a = (a + 1) / 2;
            else
                a /= 2;

            if(b % 2 != 0)
                b = (b + 1) / 2;
            else
                b /= 2;

        }
//        for(int i = 0; i < n; i++) {
//            answer++;
//            if(a % 2 != 0)
//                a = (a + 1) / 2;
//            else
//                a /= 2;
//            if(b % 2 != 0)
//                b = (b + 1) / 2;
//            else
//                b /= 2;
//            System.out.printf("i: %d, a: %d, b: %d, answer: %d \n",i,a,b,answer);
//            if(check(a, b)) break;
//                //return answer + 1;
//        }

        return answer;
    }

    public boolean check(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b); // 3 4
        if(min % 2 != 0 && min + 1 == max)
            return true;
        return false;
    }

    public static void main(String[] args) {
        int n = 8, a = 4, b = 7;
        Solution1 s1 = new Solution1();
        System.out.println(s1.solution(n,a,b));

    }
}
