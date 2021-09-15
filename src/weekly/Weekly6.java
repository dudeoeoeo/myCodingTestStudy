package weekly;

import java.util.*;

public class Weekly6 {

    class Player {
        double winLate;
        int winWeight;
        int myWeight;
        int playerNum;

        Player(double winLate, int winWeight, int myWeight, int playerNum) {
            this.winLate = winLate;
            this.winWeight = winWeight;
            this.myWeight = myWeight;
            this.playerNum = playerNum;
        }
    }
    Comparator<Player> comp = new Comparator<Player>() {
        public int compare(Player p1, Player p2) {
            if (p1.winLate != p2.winLate) {
                if (p1.winLate < p2.winLate)
                    return 1;
                else
                    return -1;
            }
            if (p1.winWeight != p2.winWeight) {
                if (p1.winWeight < p2.winWeight)
                    return 1;
                else
                    return -1;
            }
            if (p1.myWeight != p2.myWeight) {
                if (p1.myWeight < p2.myWeight)
                    return 1;
                else
                    return -1;
            }
            if (p1.playerNum < p2.playerNum)
                return -1;
            else
                return 1;
        }
    };
    public int[] solution(int[] weights, String[] head2head) {
        int record = head2head.length; // 총 경기 수 = 본인을 제외한 선수 수
        int players = head2head.length - 1; // 총 선수 4명 중 본인을 제외한 3명
        // weights.length == head2head.length
        List<Player> list = new ArrayList<>();

        for(int i = 0; i < record; i++) {
            int winLate = 0;
            int weight_win = 0;
            int player_weight = weights[i];
            int round = 0;

            for(int j = 0; j < record; j++) {
                if(i == j) continue; // i == j 라면 같은 선수이므로 건너뜀
                if(head2head[i].charAt(j) == 'W') {
                    winLate++;
                    if(player_weight < weights[j]) {
                        weight_win++;
                    }
                }
                if(head2head[i].charAt(j) != 'N')
                    round++;
            }
            // 전체 승률 1, if(승률 동일) 자신보다 무거운 복서 이긴 횟수 2
            // 자기 몸무게가 무거운 복서 3, 몸무게도 같다면 index 번호 순
            if(round > 0)
                list.add(new Player((double) winLate / round, weight_win, player_weight, i+1));
            else
                list.add(new Player((double) 0, weight_win, player_weight, i+1));
        }

        Collections.sort(list, comp);
        int[] answer = new int[record];

        for(int i = 0; i < record; i++)
            answer[i] = list.get(i).playerNum;

        return answer;
    }

    public static void main(String[] args) {
        int [] weights = {145,92,86};
        String [] head2head = {"NNN","NNL","NWN"};
        Weekly6 w6 = new Weekly6();
        w6.solution(weights, head2head);
    }
}
// List 자료형을 쓰지 않고 Array 자료형으로 해결. 미리 배열의 크기를 지정할 수 있는
// Array를 이용했다면 메모리 낭비를 좀 더 줄일 수 있지 않았을까... 정렬 시 Array도 풀이법을 알아두자..
/*
public int[] solution(int[] weights, String[] head2head) {
    int len = weights.length;
    int[][] rank = new int[len][4];
    for(int i = 0; i < len; i++) {
        int w = weights[i], cnt = 0, win = 0, over = 0;
        for(int j = 0; j < len; j++) {
            char c = head2head[i].charAt(j);
            cnt += c == 'N' ? 0 : 1;
            win += c == 'W' ? 1 : 0;
            over += c == 'W' && weights[i] < weights[j] ? 1 : 0;
        }
        rank[i][0] = i + 1;
        rank[i][1] = (int)((double)win / cnt * 10000000);
        rank[i][2] = over;
        rank[i][3] = weights[i];
    }
    Arrays.sort(rank, (a, b) -> {
        if(a[1] != b[1]) return b[1] - a[1];
        if(a[2] != b[2]) return b[2] - a[2];
        if(a[3] != b[3]) return b[3] - a[3];
        return a[0] - b[0];
    });
    int[] answer = new int[len];
    for(int i = 0; i < len; i++) answer[i] = (int)rank[i][0];
    return answer;
}
 */