package daily;

public class C1 {

    public int solution(int N) {
        String binary = Integer.toBinaryString(N);
        int answer = 0, cnt = 0;
        boolean chk = false;

        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                // 연속해서 1이 나왔다
                if (cnt == 0 && chk)
                    continue;
                if (chk) {
                    answer = Math.max(cnt, answer);
                    cnt = 0;
                } else
                    chk = true;
                continue;
            }
            if (chk)
                cnt += 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        C1 c1 = new C1();
        for (int i = 1; i < 256; i++) {
            System.out.println("i: " + i + ", sol: " + c1.solution(i));
        }
    }
}
