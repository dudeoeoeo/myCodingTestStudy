package dev;

import java.util.*;

public class D2 {

    Map<Character, Integer> block = new HashMap<>();
    boolean [][] visited;
    char [][] boards;
    int ROW, COL;

    class Block {
        int row, col;
        char alpha;
        public Block(int row, int col, char alpha) {
            this.row = row;
            this.col = col;
            this.alpha = alpha;
        }
    }

    public int solution(String[] maps) {

        ROW = maps.length; COL = maps[0].length();
        visited = new boolean[ROW][COL];
        boards = new char[ROW][COL];

        int idx = 0;
        for (String m : maps)
            boards[idx++] = m.toCharArray();

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                char now = boards[row][col];

                if (visited[row][col] || now == '.') continue;

                visited[row][col] = true;
                bfs(row, col, now);
            }

        }

        int answer = 0;

        for (int val : block.values())
            answer = Math.max(answer, val);

        return answer;
    }

    public void bfs(int row, int col, char al) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();

        Queue<Block> q = new LinkedList<>();
        q.offer(new Block(row, col, al));

        int [] dx = {-1, 1, 0, 0};
        int [] dy = {0, 0, -1, 1};

        while (q.isEmpty() == false) {
            Block now = q.poll();

            if (map.containsKey(now.alpha) == false) {
                map.put(now.alpha, 1);
                set.add(now.alpha);
            } else {
                map.put(now.alpha, map.getOrDefault(now.alpha, 0) + 1);
            }

            for (int i = 0; i < 4; i++) {
                int x = now.row + dx[i], y = now.col + dy[i];
                if (x < 0 || x >= ROW || y < 0 || y >= COL) continue;

                if (visited[x][y]) continue;
                if (boards[x][y] == '.') continue;

                visited[x][y] = true;

                q.offer(new Block(x, y, boards[x][y]));
            }
        }

        int max = -1, count = 0, sum = 0;
        char a = 'A';

        for (char key : map.keySet()) {
            int val = map.get(key);

            if (max > -1 && max < val) {
                set.remove(a);
                a = key;
                max = val;
            } else if (max > -1 && max == val) {
                a = (char) Math.max(a, key);
                count++;
            } else if (max > -1 && max > val) {
                set.remove(key);
            } else if (max == -1) {
                a = key;
                max = val;
                count++;
            }
            sum += val;
        }

        if (count == map.size()) {
            setBlockMap(map);
            return;
        } else if (count > 1) {
            setMaxBlock(map, set, a, max, sum);
            return;
        }
        block.put(a, block.getOrDefault(a, 0) + sum);
    }

    public void setMaxBlock(Map<Character, Integer> map, Set<Character> set, char key, int max, int sum) {
        sum -= max * set.size();
        block.put(key, block.getOrDefault(key, 0) + sum);
        set.remove(key);

        for (char k : map.keySet()) {
            if (set.contains(k))
                block.put(k, block.getOrDefault(k, 0) + max);
        }
    }

    public void setBlockMap(Map<Character, Integer> map) {
        for (char key : map.keySet()) {
            block.put(key, block.getOrDefault(key, 0) + map.get(key));
        }
    }

    public static void main(String[] args) {
        D2 d2 = new D2();
        String [] m = {"AABCA.QA", "AABC..QX", "BBBC.Y..", ".A...T.A", "....EE..", ".M.XXEXQ", "KL.TBBBQ"};
        System.out.println(d2.solution(m));
    }
}
