package springcodev;

import java.util.*;

public class Sp1 {
    boolean [][] visit;
    char [][] t_grid;
    boolean stop = false;
    int R, C;

    class Location {
        int row, col;
        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int solution(String[] grid) {
        R = grid.length; C = grid[0].length();
        int row = grid.length, col = grid[0].length();
        t_grid = new char[row][col];
        visit = new boolean[row][col];

        int idx = 0;
        for (String g : grid) {
            char[] chars = g.toCharArray();
            t_grid[idx++] = chars;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visit[i][j]) continue;
                if (t_grid[i][j] != '.') continue;

                visit[i][j] = true;
                bfs(i, j);
                stop = false;
            }
        }

        return getSharpCnt();
    }

    public void bfs(int row, int col) {
        int [] x = {-1, 1, 0, 0};
        int [] y = {0, 0, -1, 1};

        List<String> lo = new ArrayList<>();
        Queue<Location> q = new LinkedList<>();
        q.offer(new Location(row, col));

        while (!q.isEmpty()) {
            final Location location = q.poll();
            lo.add(location.row + "," + location.col);
            for (int i = 0; i < 4; i++) {
                int nx = location.row + x[i], ny = location.col + y[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    stop = true;
                    continue;
                }

                if (visit[nx][ny] || t_grid[nx][ny] == '#') {
                    continue;
                }

                q.offer(new Location(nx, ny));
                visit[nx][ny] = true;
            }
        }

        if (!stop) {
            transform(lo);
        }
    }

    public void transform(List<String> locations) {
        for (String l : locations) {
            String [] lo = l.split(",");
            t_grid[Integer.parseInt(lo[0])][Integer.parseInt(lo[1])] = '#';
        }
    }

    public int getSharpCnt() {
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (t_grid[i][j] == '#') cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Sp1 s1 = new Sp1();
        final int solution = s1.solution(new String[]{".....####", "..#...###", ".#.##..##", "..#..#...", "..#...#..", "...###..."});
        System.out.println(solution);
    }
}
