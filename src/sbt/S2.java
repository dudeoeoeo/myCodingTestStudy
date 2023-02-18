package sbt;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class S2 {

    boolean [][] visited;
    int [][] board;
    int ROW, COL;
    int answer = Integer.MAX_VALUE, c = 0;

    class Node {
        int x, y, sum;
        public Node (int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }
        public String toString() {
            return "x: " + this.x + ", y: " + this.y + ", sum: " + this.sum;
        }
    }

    public int solution(int[][] grid) {
        ROW = grid.length; COL = grid[0].length;
        visited = new boolean[ROW][COL];
        board = new int[ROW][COL];

        for(int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = grid[i][j];
            }
        }

        bfs(grid);

        return answer;
    }

    void bfs(int [][] grid) {
        int [] dx = {1, 0};
        int [] dy = {0, 1};

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, board[0][0])); // 출발점
        visited[0][0] = true;

        while (q.isEmpty() == false) {
            Node curr = q.poll();
            // System.out.println(curr.toString());
            for (int [] g : grid)
                System.out.println(Arrays.toString(g));

            if (c >= ROW * COL) return;

            for (int i = 0; i < 2; i++) {
                int nx = curr.x + dx[i] >= ROW ? curr.x : curr.x + dx[i];
                int ny = curr.y + dy[i] >= COL ? curr.y : curr.y + dy[i];
                // System.out.println(nx + ", " + ny + ", " + grid[nx][ny]);

                // 이미 방문한 곳이라면 값을 비교해서 작은 값으로 대체
                if (curr.x == ROW - 1 && curr.y == COL - 1) {
                    check(curr.x, curr.y, curr.sum);
                    continue;
                }
                if (visited[nx][ny]) {
                    board[nx][ny] = Math.min(board[nx][ny], grid[nx][ny] + curr.sum);
                    q.offer(new Node(nx, ny, board[nx][ny]));
                    continue;
                }
                visited[nx][ny] = true;
                board[nx][ny] = curr.sum + board[nx][ny];
                q.offer(new Node(nx, ny, board[nx][ny]));
            }
        }
    }
    void check(int x, int y, int sum) {
        // System.out.println("check => " + x + " " + y + " " + sum);
        if (x == ROW - 1 && y == COL - 1) {
            answer = Math.min(answer, sum);
            c++;
        }
    }

    public static void main(String[] args) {
        S2 s1 = new S2();
        int [][] grid = {{1,2}, {3,4}};
        s1.solution(grid);
    }
}
