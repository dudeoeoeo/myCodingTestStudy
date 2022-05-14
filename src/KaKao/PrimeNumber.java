package KaKao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class PrimeNumber {
    int ans = 0;
    public int solution(int[] nums) {

        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j < nums.length; j++) {
                int curr = nums[i] + nums[j];
                for(int k = j+1; k < nums.length; k++) {
                    curr += nums[k];
                    if(!list.contains(curr))
                        list.add(curr);
                    curr -= nums[k];
                }
            }
        }
        for(int i = 0; i < list.size(); i++) {
            getPrime(list.get(i));
        }
        return ans;
    }
    void getPrime(int now) {
        for(int i = 2; i <= Math.sqrt(now); i++) {
            if(now % i == 0) return;
        }
        ans++;
    }
    public static void main(String[] args) {
        PrimeNumber pn = new PrimeNumber();
        int [] nums = {1,2,7,6,4};
        System.out.println(pn.solution(nums));
    }
}
