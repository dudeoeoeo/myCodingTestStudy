package ChannalTalk;

import java.util.HashMap;

public class S3 {

    boolean [] check = new boolean[7];
    int [][] idx = {{1,5,6}, {0,2,6}, {1,3,6}, {2,4,6}, {3,5,6}, {0,4,6}};

    int answer = 0;
    public int solution(int[] s1, int[] s2) {

        int zero = 0;
        int cnt = 0;
        boolean check_bool = true;
        HashMap<Integer, Integer> s1Map = new HashMap<>();
        HashMap<Integer, Integer> s2Map = new HashMap<>();

        for(int i = 0; i < 7; i++) {
            if(s1[i] == 0)
                zero = i;
            if(s1[i] == s2[i]) {
                check[i] = true;
                continue;
            } else {
                cnt++;
                s1Map.put(s1[i], i);
                s2Map.put(s2[i], i);
            }
            check_bool = false;
        }
        if(check_bool) return 0;

        for (int i = 0; i < check.length; i++) {
            if(check[i]) continue;
            int s2Idx = getNum(s2, s1[i]);
            location(s1, s2, i, s2Idx, zero);
        }

        return answer;
    }
    int getNum(int [] s2, int searchNum) {
        for(int i = 0; i < s2.length; i++) {
            if(s2[i] == searchNum)
                return i;
        }
        return 0;
    }
    public void location(int [] s1, int [] s2, int pos, int s2Idx, int zeroIdx) {
        if(pos == 6) {
            int temp = s1[pos];
            s1[pos] = s1[zeroIdx];
            s1[zeroIdx] = temp;
            return;
        }
        // idx
        for(int i = 0; i < s1.length; i++) {
            //if()
        }
    }
    boolean Move(int [] s1, int zero, int pos, int s2Idx) {
        for(int i = 0; i < idx[pos].length; i++) {
            if(idx[pos][i] == s2Idx)
                return true;
        }
        return false;
    }
}
