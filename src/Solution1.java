import java.util.Arrays;

public class Solution1 {
    int block = 0;
    public int solution(String [] board) {
        int size = board.length;
        int [][] ans = new int[size][2];
        String [][] boards = new String[size][size];

        for(int i=0; i<size; i++) {
            boards[i] = board[i].split("");
            System.out.println(Arrays.toString(boards[i]));
        }
        String preBlock = "";
        for(int i = 0; i < size; i++) {
            boolean [][] visited = new boolean[size][size];
            // row
            for(int j = 0; j < size; j++) {
                if(boards[i][j].equals(preBlock)) continue;
                preBlock = boards[i][j];
                check(boards, i, j, visited); // preBlock = 현재 "";
            }
            ans[i][0] = block;
            block = 0;
            preBlock = "";
            // column
            visited = new boolean[size][size];
            for(int k = 0; k < size; k++) {
                if(boards[k][i].equals(preBlock)) continue;
                preBlock = boards[k][i];
                check(boards, k, i, visited); // preBlock = 현재 "";
            }
            ans[i][1] = block;
            block = 0;
            preBlock = "";
        }

        for(int i=0; i<ans.length; i++) {
            System.out.printf("i: %d, ans[%d][0]: %d, ans[%d][1]: %d \n",i, i, ans[i][0], i, ans[i][1]);
            int max = Math.max(ans[i][0], ans[i][1]);
            block = Math.max(block, max);
        }
        return block;
    }
    void check(String [][] boards, int row, int col, boolean [][] visited) {
        String pre = boards[row][col];
        getBlock(boards, row, col, pre, visited);
    }
    boolean getBlock(String [][] boards, int row, int col, String pre_block, boolean [][] visited) {
        int len = boards.length;
        // 인덱스가 갈 수 없는 방향으로 초과면 return
        if(row < 0 || row >= len || col < 0 || col >= len)
            return false;
        // 이미 방문했다면 return
        if(visited[row][col])
            return false;
        String curr = boards[row][col];
        // 글자가 같지 않다면 return
        if(!curr.equals(pre_block))
            return false;
        // 글자가 같다면 visited에 마크
        if(curr.equals(pre_block)) {
            visited[row][col] = true;
            block++;
        }
        getBlock(boards, row, col+1, curr, visited);  // 오른쪽
        getBlock(boards, row, col-1, curr, visited);  // 왼쪽
        getBlock(boards, row-1, col, curr, visited); // 위쪽
        getBlock(boards, row+1, col, curr, visited); // 아래쪽
        return false;
    }
    public static void main(String[] args) {
        String [] board = {"ABBBBC", "AABAAC", "BCDDAC", "DCCDDE", "DCCEDE", "DDEEEB"};
        Solution1 s = new Solution1();
        System.out.println(s.solution(board));
    }

}


