package Programmers.DFS_BFS;

public class Ranking {
    public int solution(int n, int[][] results) {
        int WIN = 1;
        int LOSE = -1;

        int [][] players = new int[n + 1][n + 1];

        for (int [] result : results) {
            int winner = result[0];
            int loser = result[1];
            players[winner][loser] = WIN;
            players[loser][winner] = LOSE;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (i == k) continue;
                    // i = 1번선수 , k = 1번이 이긴선수 , j = 1번이 이긴 선수가 이긴 선수
                    if (players[i][k] == 1 && players[k][j] == 1) {
                        // i 가 k 에게 이겼으니 k가 이긴 j를 이긴다
                        players[i][j] = 1;
                        // j 는 k 에게 졌으니 k가 진 i 에게도 패배한다
                        players[j][i] = -1;
                    }
                    // 4 > 1 , 5 > 4
                    // i = 1번선수 , k = 1번이 진 선수 , j = k 가 진 선수
                    if (players[i][k] == -1 && players[k][j] == -1) {
                        // i 는 k 에게 졌으니 k 를 이긴 j 선수는 i 를 이긴다
                        players[j][i] = 1;
                        // i 는 k 에게 졌으니 k 가 진 j 선수에게 진다
                        players[i][j] = -1;
                    }
                }
            }
        }

        int answer = 0;
        for (int [] player : players) {
            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (player[i] == 0) cnt++;
            }
            if (cnt == 1) answer++;
        }

        return answer;
    }
}
