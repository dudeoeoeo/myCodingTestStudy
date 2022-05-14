package ChannalTalk;

import java.util.Arrays;

public class S2 {
    boolean [][] check;
    boolean [][] goBool;
    boolean exitBool = false;
    boolean cntBoo = false;
    int answer = 0;

    public int solution(int[][] cars) {

        int len = cars.length;
        check = new boolean[len][len];
        goBool = new boolean[len][len];
        String minSu = "";
        String exit = "";
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                if(cars[i][j] == 0)
                    goBool[i][j] = true;
                else if(cars[i][j] == 2)
                    goBool[i][j] = true;
                else if(cars[i][j] == 1)
                    minSu = String.valueOf(i) + "," + String.valueOf(j);
                else if(cars[i][j] == 4)
                    exit = String.valueOf(i) + "," + String.valueOf(j);
            }
        }
        location(cars, minSu, exit);
        if(!exitBool) return -1;
        return answer;
    }

    void dfs(int [][] cars, int row, int col) {
        int len = cars.length;
        if(exitBool)
            return;
        if(row >= len || row < 0 || col >= len || col < 0)
            return;
        if(cars[row][col] == 3)
            return;
        if(cars[row][col] == 4) {
            exitBool = true;
            return;
        }
        if(!cntBoo) {
            if (cars[row][col] == 2) {
                if (col + 1 < len && cars[row][col + 1] == 0) {
                    cars[row][col] = 0;
                    cntBoo = true;
                    answer++;
                } else if (row + 1 < len && cars[row + 1][col] == 0) {
                    cars[row][col] = 0;
                    cntBoo = true;
                    answer++;
                } else if (col - 1 >= 0 && cars[row][col - 1] == 0) {
                    cars[row][col] = 0;
                    cntBoo = true;
                    answer++;
                } else if (row - 1 >= 0 && cars[row - 1][col] == 0) {
                    cars[row][col] = 0;
                    cntBoo = true;
                    answer++;
                }
            }
        }
        if(check[row][col])
            return;
        check[row][col] = true;

        if(cars[row][col] == 0 || cars[row][col] == 1) {
            dfs(cars, row + 1, col);
            dfs(cars, row, col + 1);
            dfs(cars, row - 1, col);
            dfs(cars, row, col - 1);
        }
    }

    void dfsA(int [][] cars, int row, int col) {
        int len = cars.length;
        if(exitBool)
            return;
        if(row >= len || row < 0 || col >= len || col < 0)
            return;
        if(cars[row][col] == 3)
            return;
        if(cars[row][col] == 4) {
            exitBool = true;
            return;
        }
        if(cars[row][col] == 2)
            return;
        if(check[row][col])
            return;
        check[row][col] = true;

        dfsA(cars, row + 1, col);
        dfsA(cars, row, col + 1);
        dfsA(cars, row - 1, col);
        dfsA(cars, row, col - 1);

    }

    public void location(int [][] cars, String minsu, String exit) {
        int row = Integer.parseInt(minsu.split(",")[0]);
        int col = Integer.parseInt(minsu.split(",")[1]);
        dfsA(cars, row, col);
        if(!exitBool)
            dfs(cars, row, col);
        if(!exitBool)
            dfsA(cars, row, col);
    }


    public static void main(String[] args) {
        int [][] cars = {{0,0,3,0,0,0,0},{1,0,3,0,0,0,0},{0,0,3,0,0,0,0}, {0,0,2,0,0,3,3},{2,2,2,0,2,0,0},{3,3,2,0,2,0,3},{3,3,2,0,2,0,4}};
        S2 s2 = new S2();

        for(int [] i : cars)
            System.out.println(Arrays.toString(i));
        int [][] cars2 = {{0,2,0,0,0},{0,4,2,0,0},{0,2,0,0,0},{0,0,0,2,1},{0,0,0,2,0}};
        s2.solution(cars);
    }
}
