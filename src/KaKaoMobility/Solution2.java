package KaKaoMobility;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    public int solution(int[] T, int[] A) {
        // write your code in Java SE 8
        List<Integer> list = new ArrayList<>();
        List<Integer> must = new ArrayList<>();
        for(int a : A)
            must.add(a);
        for(int i = 0; i < A.length; i++) {
            int pre_skill = T[A[i]];
            int now_skill = A[i];
            if(!list.contains(pre_skill))
                list.add(pre_skill);
            if(!list.contains(now_skill) && must.contains(now_skill))
                list.add(pre_skill);
        }
        return list.size();
    }

    public static void main(String[] args) {
        Solution2 s2 = new Solution2();
        int [] T = {0,0,0,0,2,3,3};
        int [] T1 = {0,0,1,1};
        int [] A = {2,5,6};
        int [] A2 = {2};
        System.out.println(s2.solution(T1, A2));
    }
}
