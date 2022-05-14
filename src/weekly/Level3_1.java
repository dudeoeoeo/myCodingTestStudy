package weekly;

import java.util.Arrays;

public class Level3_1 {
    int N, minCnt = 10;
    int [] Weak;
    int [] Dist;

    public int solution(int n, int[] weak, int[] dist) {
        Arrays.sort(dist);
        N = n;
        Weak = weak;
        Dist = dist;

        for(int i = 0; i < weak.length; i++) {
            boolean [] visited = new boolean[weak.length];
            System.out.println("==============================================================================");
            solve(1, i,0, visited);
        }

        if(minCnt == 10)
            return -1;
        return minCnt;
    }

    void solve(int friend_cnt, int now_pos, int visit_cnt, boolean [] visited) {
        System.out.println("top "+Arrays.toString(visited)+", now_pos:"+now_pos+", visit_cnt: "+visit_cnt);
        if(friend_cnt > Dist.length) return;
        if(friend_cnt >= minCnt) return;
        System.out.println(Arrays.toString(visited)+", visit_cnt: "+visit_cnt);
        for(int i = 0; i < Weak.length; i++) {
            int next_pos = (now_pos + i) % Weak.length; // 0 + 0 / 4= 0; 0 + 1 % 4 = 1; ... 2 + 3 % 4 = 1;   1, 5, 6, 10; 5, 6, 10, 1; 6, 10, 1, 5; 10, 1, 5, 6;
            int diff = Weak[next_pos] - Weak[now_pos]; // 처음은 1 - 1 = 0, 2번 째 5 - 1 = 4, 3번 째 6 - 1 = 5, 4번 째 10 - 1 = 9;    1 - 5 = -4, 12 + 1 - 5 = 8


            // 취약점은 작은 수부터 오므로 현재 포지션이 다음 포지션보다 크다는 의미는 한 바퀴를 돌았다는 의미
            if(next_pos < now_pos) // now_pos가 4으로 호출됐는데 next_pos가 0이라서 4 - 0 % 4 = 0
                diff += N;

            // 현재 친구가 점검할 수 있는 거리보다 취약점의 거리가 더 크다면 break
            if(diff > Dist[Dist.length - friend_cnt])  // 친구 한명의 거리, 친구가 2명이 되면  친구들이 이동할 수 있는 거리의 배열 길이 - 현재 점검해야 할 친구들 수
                break;
            // visit_cnt++;
            visit_cnt |= 1 << next_pos;
            visited[next_pos] = true;
        }

        if(visit_cnt == (1 << Weak.length) -1) {
            minCnt = friend_cnt;
            return;
        }
        /*
        if(visit_cnt == Weak.length) {
            System.out.println("visit_cnt == Weak.length - 1, "+visit_cnt+", friend_cnt: "+friend_cnt);
            minCnt = friend_cnt;
            return;
        }*/

        for(int i = 0; i < Weak.length; i++) {
            if(visited[i]) continue;
            System.out.println("FOR LOOP : "+Arrays.toString(visited)+", i: "+i);
            solve(friend_cnt + 1, i, visit_cnt, visited);
        }
    }

    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        Level3_1 l3_1 = new Level3_1();
        System.out.println(l3_1.solution(n, weak, dist));
    }
}

/*
    void solve(int friend_cnt, int now_pos, int visit_cnt) {
        if(friend_cnt > Dist.length) return;
        if(minCnt <= friend_cnt) return;
        for(int i = 0; i < Weak.length; i++) {
            int next_pos = (now_pos + i) % Weak.length; // 0 + 0 / 4= 0; 0 + 1 % 4 = 1; ... 2 + 3 % 4 = 1;   1, 5, 6, 10; 5, 6, 10, 1; 6, 10, 1, 5; 10, 1, 5, 6;
            int diff = Weak[next_pos] - Weak[now_pos]; // 처음은 1 - 1 = 0, 2번 째 5 - 1 = 4, 3번 째 6 - 1 = 5, 4번 째 10 - 1 = 9;    1 - 5 = -4, 12 + 1 - 5 = 8

            // 취약점은 작은 수부터 오므로 현재 포지션이 다음 포지션보다 크다는 의미는 한 바퀴를 돌았다는 의미
            if(next_pos < now_pos) // now_pos가 4으로 호출됐는데 next_pos가 0이라서 4 - 0 % 4 = 0
                diff += N;

            // 현재 친구가 점검할 수 있는 거리보다 취약점의 거리가 더 크다면 break
            if(diff > Dist[Dist.length - friend_cnt])  // 친구 한명의 거리, 친구가 2명이 되면  친구들이 이동할 수 있는 거리의 배열 길이 - 현재 점검해야 할 친구들 수
                break;
            visit_cnt |= 1 << next_pos;
        }

        if(visit_cnt == (1 << Weak.length) -1) {
            minCnt = friend_cnt;
            return;
        }

        for(int i = 0; i < Weak.length; i++) {
            if((visit_cnt & (1 << i)) != 0) continue;

            solve(friend_cnt + 1, i, visit_cnt);
        }
    }
 */