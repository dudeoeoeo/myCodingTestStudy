package KaKao;

import java.util.*;

public class ColorBook {
    boolean [][] visited;
    int maxColor;
    int[][] board;
    int M, N;

    public int[] solution(int m, int n, int[][] picture) {
        ArrayList<Integer> list = new ArrayList<>();

        M = m;
        N = n;
        board = new int[M][N];
        visited = new boolean[M][N];
        for(int i =0;i < M; i++){
            for(int j = 0; j < N; j++){
                board[i][j] = picture[i][j];
                visited[i][j] = false;
            }
        }

        int sortNum = 0;
        for (int row = 0; row < M; row++) {
            maxColor = 0;
            for (int col = 0; col < N; col++) {
                int nowNum = board[row][col];
                if (visited[row][col]) continue;
                if (nowNum == 0) {
                    visited[row][col] = true;
                    continue;
                }
                bfs(row, col, nowNum, board);
                sortNum++;
            }
            list.add(maxColor);
        }
        int max = 0;
        for (int num : list) {
            max = Math.max(max, num);
        }
        int[] answer = new int[2];
        answer[0] = sortNum;
        answer[1] = max;
        return answer;
    }

    public void bfs(int row, int col, int searchNum, int [][] board) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return;

        if (visited[row][col]) return;

        if (searchNum == board[row][col]) {
            int [] dx = {-1, 0, 1, 0};
            int [] dy = {0, 1, 0, -1};
            visited[row][col] = true;
            maxColor++;
            for (int i = 0; i < 4; i ++) {
                bfs(row + dx[i], col + dy[i], searchNum, board);
            }
        }
    }
}
