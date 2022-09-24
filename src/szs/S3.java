package szs;

import java.util.Arrays;

public class S3 {

    char [][] boards;
    StringBuilder sb = new StringBuilder();
    String[] answer;
    int ROW, COL;

    public String[] solution(String[] board, int y, int x) {
        ROW = board.length;
        COL = board[0].length();
        boards = new char[ROW][COL];
        answer = new String[ROW];

        int idx = 0;
        for (String b : board)
            boards[idx++] = b.toCharArray();

        if (boards[y][x] == 'M') {
            mineClick(y, x);
        } else {
            bfs(y, x);
        }

        return answer;
    }

    void bfs(int y, int x) {
        char check = mineCheck(y, x);
        if (check != 'B') {
            boards[y][x] = check;
            fillBoardWithE();
            return;
        }
        boards[y][x] = 'B';

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.println(boards[i][j]);
                boards[i][j] = mineCheck(i, j);
            }
        }

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.println(boards[i][j]);
                boards[i][j] = mineCheck(i, j);
            }
        }

        fillBoardWithE();
    }

    /**
     * TODO: class 로 변경 후 bfs 수행하면서 블록 채우기
     */
    void fillBoardWithE() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (boards[i][j] == 'M')
                   sb.append('E');
                else if (boards[i][j] == 'E')
                    sb.append('E');
                else
                    sb.append(boards[i][j]);
            }
            answer[i] = sb.toString();
            sb.setLength(0);
        }
    }

    char mineCheck(int row, int col) {
        if (boards[row][col] == 'M') {
            return 'M';
        }
        // 왼쪽 대각, 위, 오른쪽 대각,
        // 왼쪽, 오른쪽,
        // 왼쪽 아래 대각, 밑, 오른쪽 아래 대각
        int [] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
        int [] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

        // B, M, E, num
        int [] arr = new int[4];

        for (int i = 0; i < 8; i++) {
            int ny = row + dy[i];
            int nx = col + dx[i];

            if (ny < 0 || ny >= ROW || nx < 0 || nx >= COL)
                continue;

            if (boards[ny][nx] == 'B')
                arr[0]++;
            else if (boards[ny][nx] == 'M')
                arr[1]++;
            else if (boards[ny][nx] == 'E')
                arr[2]++;
            else
                arr[3]++;
        }
        // mine exists
        if (arr[1] > 0) {
            // active button not exists
            if (arr[0] == 0)
                return 'E';
            return String.valueOf(arr[1]).charAt(0);
        }
        if (arr[0] > 0) {
            return 'B';
        }
        if (arr[1] == 0 && arr[3] == 0 && arr[2] > 0)
            return 'B';
        return 'E';
    }

    void mineClick(int y, int x) {
        boards[y][x] = 'X';
        for (int i = 0; i < ROW; i++) {

            for (int j = 0; j < COL; j++) {
                sb.append(boards[i][j]);
            }
            answer[i] = sb.toString();
            sb.setLength(0);
        }
    }

    public static void main(String[] args) {
        S3 s3 = new S3();
        String [] bb = {"MME", "EEE", "EME"};
        int y = 0, x = 0;
        System.out.println(Arrays.toString(s3.solution(bb, y, x)));
    }
}
