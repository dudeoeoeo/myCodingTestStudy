package szs;

import java.util.Arrays;
import java.util.Comparator;

public class S1 {

    public int[] solution(String[] students) {
        int len = students.length;
        int [] answer = new int[len];
        int [][] scores = new int[len][2];

        for (int i = 0; i < len; i++) {
            scores[i][0] = setScore(students[i]);
            scores[i][1] = i + 1;
        }

        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] t2, int[] t1) {
                if (t1[0] > t2[0])
                    return 1;
                else if (t1[0] < t2[0])
                    return -1;
                else if (t1[0] == t2[0]) {
                    if (t1[1] > t2[1])
                        return -1;
                }
                return 0;
            }
        });

        for (int i = 0; i < len; i++) {
            answer[i] = scores[i][1];
        }

        return answer;
    }

    public int setScore(String student) {
        int [] score = new int[3];

        for (int i = 0; i < 10; i++) {
            char now = student.charAt(i);
            switch (now) {
                case 'A':
                    score[0]++;
                    break;
                case 'L':
                    score[1]++;
                    break;
                default:
                    score[2]++;
            }
        }

        if (score[1] / 2 >= 3 || (score[2] + (score[1] / 2)) > 2) {
            return 0;
        }
        int lazyScore = score[1] / 2;
        int totalScore = (score[0] - score[2]) - lazyScore;
        return 10 + totalScore;
    }

    public static void main(String[] args) {
        String [] st = {"AAALLLAPAA", "AAAAAAAPPP", "ALAAAAPAAA"};
        S1 s1 = new S1();
        System.out.println(Arrays.toString(s1.solution(st)));
    }
}
