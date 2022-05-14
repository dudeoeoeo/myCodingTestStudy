package Programmers.DFS_BFS;

public class Network {

    public int solution(int n, int [][] computers) {
        int answer = 0;
        boolean [] visited = new boolean[n];
        for(int row = 0; row < n; row++) {
            if(!visited[row]) {
                dfs(computers, row, visited);
                answer++;
            }
        }
        return answer;
    }

    public void dfs(int [][] computers, int row, boolean [] visited) {
        visited[row] = true;
        for(int r = 0; r < computers.length; r++) {
            if(computers[row][r] == 1 && !visited[r]) {
                dfs(computers, r, visited);
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int [][] computers = {{1, 1, 0, 1}, {1, 1, 0, 0}, {0, 0, 1, 1}, {1, 0, 1, 1}};
        int [][] computers1 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int [][] computers2 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        Network nw = new Network();
        int ans = nw.solution(computers2.length, computers2);
        System.out.println(ans);
    }
}

/*
boolean [][] visited;
    boolean [] vv;
    boolean cnt_bool;
    int network_cnt;
    public int solution(int n, int [][] computers) {
        if(computers.length == 1) {
            return (computers[0][0] == 1 ? 1 : 0);
        }
        int answer = 0;
        visited = new boolean[n][n];
        vv = new boolean[n];
        for(int row = 0; row < n; row++) {
            cnt_bool = false;
            for(int col = 0; col < n; col++) {
                if(computers[row][col] != 0 && !vv[row]) {
                    vv[row] = true;
                    cnt_bool = true;
                    bfs(computers, row, col);
                }
            }
            if(cnt_bool)
                answer++;
            if(!Arrays.toString(vv).contains("false"))
                break;
        }
        return answer;
    }

    public void bfs(int [][] computers, int row, int col) {
        network_cnt++;
        int len = computers.length;
        if(row >= len || row < 0 || col >= len || col < 0)
            return;
        // 0번째 행의 0번째 컬럼부터 돌고
        // 0번째 컬럼부터 돌 때
        for(int i = 0; i < len; i++) {
            if(computers[row][i] == 0) continue;
            if(i == row) continue; // 현재 i와 row는 자신의 번호이므로 건너뜀
            // 여기를 들어왔다는건 현재 행의 i번이 1이라는 뜻
            if(vv[i]) continue; // 방문한 적이 있다면 건너뜀
            vv[i] = true;
            cnt_bool = true;
            bfs(computers, i, 0);
        }
    }
 */