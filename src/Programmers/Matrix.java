package Programmers;

public class Matrix {
    int[][] answer, lt;
    int ROW, COL;

    public int[][] solution(int[][] rc, String[] operations) {
        ROW = rc.length; COL = rc[0].length;

        answer = new int[ROW][COL];
        for (int i = 0; i < ROW; i++)
            answer[i] = rc[i];

        int totalRotate = (ROW * 2) + (COL - 2) + (COL - 2);
        lt = new int[totalRotate][2];

        int row = 0, col = 0, idx = 0;
        for (int i = 0; i < totalRotate; i++) {
            if (idx == 0) {
                lt[i][0] = row;
                lt[i][1] = col++;
                if (col == COL - 1)
                    idx++;
            } else if (idx == 1) {
                lt[i][0] = row++;
                lt[i][1] = col;
                if (row == ROW - 1)
                    idx++;
            } else if (idx == 2) {
                lt[i][0] = row;
                lt[i][1] = col--;
                if (col == 0)
                    idx++;
            } else {
                lt[i][0] = row--;
                lt[i][1] = col;
            }
        }

        int shiftNum = 0, rotateNum = 0;

        for (String o : operations) {
            if (o.equals("ShiftRow")) {
                shiftNum++;
                rotateNum = rotateNum % totalRotate;
                if (rotateNum > 0) {
                    rotate(rotateNum, totalRotate);
                    rotateNum = 0;
                }
            } else {
                shiftNum = shiftNum % ROW;
                if (shiftNum > 0) {
                    shiftRow(shiftNum);
                    shiftNum = 0;
                }
                rotateNum++;
            }
        }

        rotateNum = rotateNum % totalRotate;
        if (rotateNum > 0) {
            rotate(rotateNum, totalRotate);
        }
        shiftNum = shiftNum % ROW;
        if (shiftNum > 0) {
            shiftRow(shiftNum);
        }

        return answer;
    }

    /**
     * TODO: 효율성 통과를 위해 O(n) 으로 줄여야 함
     */
    void rotate(int num, int loop) {
        // int [] temp = new int[loop];

        int first = answer[0][0], past = 0;
        int key = 0;
        int val = loop - num;
        while (key < loop - 1) {
            int mx = lt[key][0], my = lt[key++][1]; // save
            int x = lt[val][0], y = lt[val][1]; // move
            // answer[mx][my] = answer[x][y]; // 1
            int temp = answer[x][y];
            past = answer[mx][my];
            answer[mx][my] = temp; // 4

            // temp[key++] = answer[x][y];
            if (++val >= loop)
                val = 0;
        }
        answer[lt[key][0]][lt[key][1]] = first;

        // for (int idx = 0; idx < loop; idx++) {
        //     int x = lt[idx][0], y = lt[idx][1];
        //     answer[x][y] = temp[idx];
        // }
    }

    void shiftRow(int num) {
        int [][] temp = new int[ROW][COL];

        int idx = 0, move = num;

        while (idx < ROW) {
            temp[move] = answer[idx++];
            if (++move >= ROW)
                move = 0;
        }
        for (int i = 0; i < ROW; i++)
            answer[i] = temp[i];
    }
}
