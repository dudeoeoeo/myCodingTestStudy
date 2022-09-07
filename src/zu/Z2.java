package zu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Z2 {

    boolean [] printed;
    int[] answer;
    int time = 0, idx = 0, print_num = 0, len = 0;
    PriorityQueue<Paper> q = new PriorityQueue<>();

    class Paper implements Comparable<Paper> {
        int num, time, page;
        public Paper(int num, int time, int page) {
            this.num = num;
            this.time = time;
            this.page = page;
        }

        public int compareTo(Paper next) {
            if (this.page == next.page) {
                return this.num - next.num;
            }
            return this.page - next.page;
        }
    }

    public int[] solution(int[][] data) {

        Arrays.sort(data, new Comparator<int[]>() {
            public int compare(int[] t1, int[] t2) {
                // 프린터 요청 시간 정렬
                if (t1[1] < t2[1]) {
                    return -1;
                    // 프린터 요청 시간이 같다면 페이지 수로 정렬
                } else if (t1[1] == t2[1]) {
                    if (t1[2] < t2[2]) {
                        return -1;
                    }
                    return 0;
                }
                return 0;
            }
        });

        len = data.length;
        printed = new boolean[len + 1];
        answer = new int[len];

        for (int i = 0; print_num < len; ) {
            if (printed[data[i][0]] == false) {
                q.add(new Paper(data[i][0], data[i][1], data[i][2]));
                printed[data[i][0]] = true;
                i++;
            }

            // q 를 먼저 비움
            setAnswer();
            // 비우고 난 뒤에 현재 출력된 시간까지 프린터 탐색
            setPrint(data);
        }

        while (q.isEmpty() == false) {
            Paper curr = q.poll();
            answer[idx++] = curr.num;
        }
        return answer;
    }

    public void setAnswer() {
        if (q.isEmpty()) return;
        Paper curr = q.poll();
        answer[idx++] = curr.num;
        if (time < curr.time)
            time += curr.time;
        time += curr.page;
        print_num++;
    }

    public void setPrint(int [][] data) {
        for (int i = idx; i < len; i++) {
            if (printed[data[i][0]]) continue;
            if (data[i][1] > time) {
                if (q.isEmpty()) {
                    time = data[i][1];
                }
                return;
            }
            if (data[i][1] <= time) {
                q.add(new Paper(data[i][0], data[i][1], data[i][2]));
                printed[data[i][0]] = true;
            }
        }
    }

    public static void main(String[] args) {
        Z2 z2 = new Z2();
        int [][] data1 = {{1, 4, 10}, {2, 2, 8}, {3, 3, 9}, {4, 1, 6}, {5, 0, 5}};
        int [][] data2 = {{1, 2, 9}, {2, 2, 8}, {3, 2, 8}, {4, 0, 2}, {5, 0, 3}};

        int [][] data3 = {{1, 0, 5}, {2, 2, 2}, {3, 3, 1}, {4, 4, 1}, {5, 10, 2}};

        int [][] data4 = {{1, 0, 3}, {2, 1, 3}, {3, 3, 2}, {4, 9, 1}, {5, 10, 2}};

        int [][] data5 = {{1, 2, 10}, {2, 5, 8}, {3, 6, 9}, {4, 20, 6}, {5, 25, 5}};
//        int [][] data = {{1, 4, 10}, {2, 2, 8}, {3, 3, 9}, {4, 1, 6}, {5, 0, 5}};
//        int [][] data = {{1, 4, 10}, {2, 2, 8}, {3, 3, 9}, {4, 1, 6}, {5, 0, 5}};
        System.out.println(Arrays.toString(z2.solution(data1)));
        Z2 z21 = new Z2();
        System.out.println(Arrays.toString(z21.solution(data2)));

        Z2 z22 = new Z2();
        System.out.println(Arrays.toString(z22.solution(data3)));

        Z2 z23 = new Z2();
        System.out.println(Arrays.toString(z23.solution(data4)));

        Z2 z24 = new Z2();
        System.out.println(Arrays.toString(z24.solution(data5)));
    }
}
