package Programmers;

import java.util.ArrayList;
import java.util.List;

public class Printer {
    public int solution(int[] priorities, int location) {
        int search = priorities[location]; // 찾을 숫자
        int mv_idx = 0;
        List<Integer> numList = new ArrayList<>();

        for(int i=0; i<priorities.length; i++) {
            if(priorities[i] < search) continue;
            if(i == location) mv_idx = numList.size();
            numList.add(priorities[i]);
        }
        if(numList.size() == 1) return 1;

        int size = numList.size();
        int idx = 0;
        idx = 0;

        while (true) {
            int current = numList.get(idx);
            Boolean bool = true;
            // mv_idx 가 현재 숫자라면 비교하고 제일 크면 return 아니면 끝으로 보냄
            if(idx == mv_idx) {
                for(Integer num : numList) {
                    // 현재 보고 싶은 숫자가 뒤에 숫자보다 작다면 뒤로 보냄
                    if(current < num) {
                        numList.remove(idx);
                        numList.add(current);
                        mv_idx = numList.size()-1;
                        bool = false;
                        break;
                    }
                }
                // 현재 찾고자 하는 숫자가 가장 크다면 인쇄하므로 return
                if(bool) return size - numList.size() + 1;
                // 현재 숫자가 mv_idx 와 같지 않을 때
            } else {
                // 현재 숫자가 제일 크면 인쇄하므로 -1 or 작아도 뒤로 보내지므로 -1
                --mv_idx;
                for(Integer num : numList) {
                    // 현재 숫자가 뒤에 숫자보다 작다면 뒤로 보냄
                    if(current < num) {
                        numList.remove(idx);
                        numList.add(current);
                        bool = false;
                        break;
                    }
                }
                // 현재 숫자가 가장 크다면 인쇄하므로 remove
                if(bool) numList.remove(idx);
            }
        }
    }
}
