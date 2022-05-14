package Programmers.DFS_BFS;

import java.util.LinkedList;
import java.util.Queue;

// 게임 맵 최단거리
public class ShortDistance {

    class Node {
        int x, y, move;
        public Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    int R, C;
    boolean [][] visit;

    public int solution(int[][] maps) {
        R = maps.length;
        C = maps[0].length;
        visit = new boolean[R][C];

        return bfs(0, 0, maps);
    }

    public int bfs(int row, int col, int [][] maps) {
        int [] dx = {0, -1, 0, 1};
        int [] dy = {-1, 0, 1, 0};
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col, 1));
        visit[row][col] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == R - 1 && now.y == C - 1)
                return now.move;

            for (int i = 0; i < 4; i++) {
                int x = now.x + dx[i];
                int y = now.y + dy[i];
                // 허용된 범위를 넘는지 확인
                if (x >= 0 && x < R && y >= 0 && y < C) {
                    // 다음 위치가 이동 가능하고 방문하지 않았다면 OK
                    if (maps[x][y] == 1 && !visit[x][y]) {
                        visit[x][y] = true;
                        q.offer(new Node(x, y, now.move + 1));
                    }
                }
            }
        }
        return -1;
    }
}
