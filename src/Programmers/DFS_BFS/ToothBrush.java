package Programmers.DFS_BFS;

import java.util.*;

public class ToothBrush {
    int [][] people; // 부모 노드 번호, 가진 돈
    Map<String, Integer> nodeNum = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
        people = new int[N][2];

        for (int i = 0; i < N; i++) {
            String now = enroll[i]; // 본인
            String parent = referral[i];// 부모

            nodeNum.put(now, i);

            if (parent.equals("-"))
                people[i][0] = 100001;
            else
                people[i][0] = nodeNum.get(parent);
        }

        setCost(seller, amount);

        int [] answer = new int[N];

        int idx = 0;
        for (int [] p : people)
            answer[idx++] = p[1];

        return answer;
    }

    void setCost(String [] seller, int [] amount) {
        int len = seller.length;

        for (int i = 0; i < len; i++) {
            String s = seller[i];

            int price = amount[i] * 100; // 내가 가질 돈
            int divide = price / 10; // 부모 노드가 가져갈 돈

            int parentNum = people[nodeNum.get(s)][0]; // nodeNum 으로 불러온 부모노드 번호

            // 불러온 부모노드가 10만보다 크다면 "-" 므로 그냥 가격을 더함
            if (parentNum > 100000) {
                people[nodeNum.get(s)][1] += (price - divide);
                continue;
            }
            // 아닐 경우 가격 / 10  처음 판매는 무조건 1개 이상 그렇다면 10원 미만일 경우 X
            people[nodeNum.get(s)][1] += (price - divide); // 1200 - 120 1080

            while (true) {
                // 맨 처음 들어온경우는 이미 "-" 가 아니라는걸 확인했으니 돈을 분배
                // 아닐 경우 120 / 10 => 12
                int curr = (int) Math.round(divide / 10); // 돈을 먼저 나눈다

                // 120 - 12 = 108
                people[parentNum][1] += (divide - curr);

                divide = curr; // 현재 부모 노드가 가져갈 돈 12

                // 현재까지는 지금 노드가 돈을 받았고
                // 부모노드를 확인해야 함
                parentNum = people[parentNum][0]; // 새로운 부모 노드 번호

                // 현재 노드가 "-" 라면 종료 바로 밑에 노드까지 이미 돈을 배분했으니 종료
                if (parentNum > 100000) {
                    break;
                }
                // 현재 노드가 "-" 가 아니고 부모 노드에게 상납할 돈이 1원 미만이라면 본인이 다 가지고 종료
                if (divide < 10) {
                    people[parentNum][1] += divide;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        String [] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String [] pa = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String [] s = {"young", "john", "tod", "emily", "mary"};
        int [] a = {12, 4, 2, 5, 10};
        ToothBrush t = new ToothBrush();
        System.out.println(Arrays.toString(t.solution(enroll, pa, s, a)));
    }
}
