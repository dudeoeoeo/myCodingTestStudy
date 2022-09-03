package Programmers;

import java.util.Arrays;

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

    void rotate(int num, int loop) {
        int [] saved = new int[loop];

        int idx = 0, key = 0, past = 0, val = loop - num;

        while (key < loop) {
            int mx = lt[key][0], my = lt[key][1];

            if (val < loop - num)
                past = saved[idx++];
            else
                past = answer[lt[val][0]][lt[val][1]];

            saved[key] = answer[lt[key][0]][lt[key][1]];
            answer[mx][my] = past;

            if (++val >= loop)
                val = 0;
            key++;
        }
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

    public static void main(String[] args) {
        int [][] rc = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String [] operations = {"Rotate", "ShiftRow"};
        Matrix m = new Matrix();
        final int[][] solution = m.solution(rc, operations);
        for (int [] s : solution)
            System.out.println(Arrays.toString(s));
    }
}