package snack;

import java.util.Arrays;

public class Sn1 {

    int K, DAY;

    public int[] solution(int day, int k) {
        int [] answer = new int[12];
        int [] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        K = k;
        DAY = day;

        for (int i = 0; i < 12; i++) {
            answer[i] = getFirstDayOfMonth(month[i]);
        }

        return answer;
    }

    public int getFirstDayOfMonth(int month) {
        int day = 0;
        for (int i = 1; i <= month; i++) {
            if (i == K) {
                day = DAY;
            }
            if (++DAY > 6) {
                DAY = 0;
            }
        }
        return day >= 5 ? 1 : 0;
    }

    public static void main(String[] args) {
        Sn1 sn1 = new Sn1();
        System.out.println(Arrays.toString(sn1.solution(6, 1)));
    }
}
