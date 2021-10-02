package BackTracking;

import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int [] q_location;
    static int board;
    static int N;
    static int answer = 0;

    static void input() {
        N = scan.nextInt();
        board = N;
        q_location = new int[N + 1];
    }

    static boolean attackable(int r1, int c1, int r2, int c2) {
        if(c1 == c2) return true; // 같은 행
        if(r1 - c1 == r2 - c2) return true; // 왼쪽 대각선 공격
        if(r1 + c1 == r2 + c2) return true; // 오른쪽 대각선 공격
        return false;
    }

    public static void rec_func(int row) {
        if(row == board + 1) {
            answer++;
        }
        else {
            for(int i = 1; i < board; i++) {
                boolean possible = true;
                for(int j = i + 1; j <= row - 1; j++) {
                    if(attackable(row, i, j, q_location[j])) {
                        possible = false;
                        break;
                    }
                }
                if(possible) {
                    q_location[row] = i;
                    rec_func(row + 1);
                    q_location[row] = 0;
                }
            }
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        input();
        rec_func(1);
        System.out.println("answer: "+answer);
    }


}
