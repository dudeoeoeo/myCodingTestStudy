package ChannalTalk;

import java.math.BigInteger;

public class S4 {

    public String solution(int n, int m, int k) {
        String answer = "";

        BigInteger comb = combination(n, m);

        comb = BigInteger.valueOf(0);

        while(comb.compareTo(BigInteger.valueOf(k)) != 0){
            BigInteger combTemp = comb;
            comb = comb.add(combination(n - 1, m));
            if(comb.compareTo(BigInteger.valueOf(k)) == -1){
                m--;
                answer += ")";
            } else {
                n--;
                answer += "(";
                comb = combTemp;
            }
            if(n == 0 || m == 0){
                break;
            }
        }
        for(int i = 1; i <= m; i++){
            answer += ")";
        }
        for(int i = 1; i <= n; i++){
            answer += "(";
        }

        return answer;
    }

    public static BigInteger combination(int N, int M){

        BigInteger combL = new BigInteger("1");
        BigInteger combR = new BigInteger("1");

        for(int i = 1; i <= N; i++){
            combL = combL.multiply(new BigInteger(String.valueOf((N+M)-(i-1))));
            combR = combR.multiply(new BigInteger(String.valueOf(i)));
        }

        return combL.divide(combR);
    }

    public static void main(String[] args) {
        int n = 2, m = 2, k = 5;
        S4 s4 = new S4();
        System.out.println(s4.solution(n, m, k));
    }

}
