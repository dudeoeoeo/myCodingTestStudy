package co;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class So2 {

    int start, end, R, C;
    boolean [][][] visited;

    class Node {
        // 좌표, 층, 이동 횟수
        int x, y, f, move;
        public Node(int x, int y, int f, int move) {
            this.x = x;
            this.y = y;
            this.f = f;
            this.move = move;
        }
    }

    public int solution(String [][] map3d) {

        for (int i = 0; i < map3d.length; i++) {
            String temp = Arrays.toString(map3d[i]);
            if (temp.indexOf("S") > -1) {
                start = i;
                for (int j = 0; j < map3d[i].length; j++) {
                    if (map3d[i][j].indexOf("S") > -1) {
                        R = j;
                        C = map3d[i][j].indexOf("S");
                        break;
                    }
                }
            }
            if (temp.indexOf("E") > -1) {
                end = i;
            }
        }

        // 3차원 방문 기록 , 층, 행, 문자 길이
        visited = new boolean[map3d.length][map3d[0].length][map3d[0][0].length()];

        return bfs(map3d, R, C);
    }
    int bfs (String [][] map, int row, int col) {
        int [] dx = {0, -1, 0, 1};
        int [] dy = {-1, 0, 1, 0};

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col, start, 0));
        visited[start][row][col] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (map[curr.f][curr.x].charAt(curr.y) == 'E')
                return curr.move;

            if (curr.f + 1 < map.length && map[curr.f + 1][curr.x].charAt(curr.y) != 'X' && !visited[curr.f + 1][curr.x][curr.y]) {
                visited[curr.f + 1][curr.x][curr.y] = true;
                q.offer(new Node(curr.x, curr.y, curr.f + 1, curr.move + 1));
            }
            if (curr.f - 1 >= 0 && map[curr.f - 1][curr.x].charAt(curr.y) != 'X' && !visited[curr.f - 1][curr.x][curr.y]) {
                visited[curr.f - 1][curr.x][curr.y] = true;
                q.offer(new Node(curr.x, curr.y, curr.f - 1, curr.move + 1));
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i], ny = curr.y + dy[i];
                // nx 가 각 층의 행 수 보다 적고, ny 가 각 블럭의 개수보다 적음
                if (nx >= 0 && nx < map[0].length && ny >= 0 && ny < map[0][0].length()) {
                    // 다음 위치가 막혀있지 않고 방문하지 않았다면 방문
                    // 현재 층, 다음 x 좌표, 다음 y 값
                    if (map[curr.f][nx].charAt(ny) != 'X' && !visited[curr.f][nx][ny]) {
                        visited[curr.f][nx][ny] = true;
                        q.offer(new Node(nx, ny, curr.f, curr.move + 1));
                    }
                }
            }
        }
        // 길이 없으면 -1 리턴
        return -1;
    }

    public static void main(String[] args) {
        String [][] map3d = {{"XXXXX", "OOSXO", "OOXOO"}, {"XEOOO", "OXXXO", "OOOOX"}};
        String [][] map3d2 = {{"OOOOO", "OOOOO", "OOEOO", "OOOOO", "OOOOO"}, {"OOOOO", "OXXXO", "OXXXO", "OXXXO", "OOOOO"}, {"OOOOO", "OOOOO", "OOSOO", "OOOOO", "OOOOO"}};
        String [][] map3d3 = {{"SOXX", "OOXX"}, {"XXOO", "XXOE"}};
        So2 s2 = new So2();
        System.out.println(s2.solution(map3d));
        System.out.println(s2.solution(map3d2));
        System.out.println(s2.solution(map3d3));
    }
}
