import java.util.Arrays;

public class Level2_2 {

    public int[] solution(String s) {

        int[] answer = new int[2];

        int originLen = s.length();


        while (s.length() > 1) {
            if(s.contains("0")) {
                int zeroLen = s.length();
                s = s.replaceAll("0", "");
                System.out.println("0 제거 s: "+s+", "+(zeroLen-s.length()));
                answer[1] = answer[1] + (zeroLen - s.length()); // 현재 answer는 원래 s 문자열 len을 가지고 있고 zero는 0을 제거하기 전 s를 가지고 있음 만약 원래 len이 10이고 방금 0을 2개 제거하였다면 10 - (10 - 8) = 8
            }
            s = Integer.toBinaryString(s.length());
            System.out.println("2진수 변환 s: "+s);
            answer[0]++;
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public static void main(String[] args) {
        Level2_2 l22 = new Level2_2();
        String s = "110010101001";
        System.out.println(l22.solution(s));
    }

}
