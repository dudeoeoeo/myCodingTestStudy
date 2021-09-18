package level1;

import java.util.*;

public class MockTest {
    class Students1 {
        int idx;
        int ans;
        public Students1(int idx, int ans) {
            this.idx = idx;
            this.ans = ans;
        }
    }
    public int[] solution(int[] answers) {

        int [] st_ans = new int[3];

        int [] students1 = {1, 2, 3, 4, 5};
        int [] students2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int [] students3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        for(int i = 0; i < answers.length; i++) {

            if(answers[i] == students1[i % 5])
                st_ans[0]++;

            if(answers[i] == students2[i % 8])
                st_ans[1]++;

            if(answers[i] == students3[i % 10])
                st_ans[2]++;

        }

        int max = Math.max(st_ans[0], Math.max(st_ans[1], st_ans[2]));
        if(max == 0) return new int[0];

        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < st_ans.length; i++) {

            if(st_ans[i] >= max)
                list.add(i+1);

        }

        int [] ans = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        MockTest mt = new MockTest();
        int [] a = {1, 2, 3, 4, 5};
        int [] a1 = {1,3,2,4,2};
        int [] a2 = {5,4,5,5,4};
        int [] a3 = {3,3,2,4,2};
        System.out.println(Arrays.toString(mt.solution(a3)));
    }
}

/*
완전 탐색, 정렬 문제
잘못하면 시간복잡도가 크게 증가할 수 있으니
최대한 리팩토링을 하면서 효율성을 줄여나가야겠다..

int [] st_ans = new int[3];
int [] students1 = {1, 2, 3, 4, 5};
int [] students2 = {2, 1, 2, 3, 2, 4, 2, 5};
int [] students3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
for(int i = 0; i < answers.length; i++) {
    if(answers[i] == students1[i % 5]) {
        st_ans[0]++;
    }
    if(answers[i] == students2[i % 8]) {
        st_ans[1]++;
    }
    if(answers[i] == students3[i % 10]) {
        st_ans[2]++;
    }
}

List<Students1> list = new ArrayList<>();
int max = Math.max(st_ans[0], Math.max(st_ans[1], st_ans[2]));

if(max == 0) return new int[0];

for(int i = 0; i < st_ans.length; i++) {
    if(st_ans[i] >= max) {
        list.add(new Students1(i + 1, st_ans[i]));
    }
}

int [] ans = new int[list.size()];
for(int i = 0; i < list.size(); i++) {
    ans[i] = list.get(i).idx;
}

return ans;
 */