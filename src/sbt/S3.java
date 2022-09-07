package sbt;

import java.util.*;

public class S3 {

    class Stone {
        int idx, jump;
        public Stone (int idx, int jump) {
            this.idx = idx;
            this.jump = jump;
        }
    }
    public int solution(int[] nums) {
        // 예시로는 징검다리에 쓰여진 점프만큼 무조건 점프
        // 그렇지 않다면 처음에 4 만큼 뛰지 않고 3번만 점프하고 3번째 징검다리에서 3을 점프하면
        // 최소 횟수 2로 건너뛸 수 있음
        // 그러므로 각 징검다리에 쓰여진 숫자만큼 무조건 뛰어야 함
        boolean [] visited = new boolean[nums.length];
        return binarySearch(nums, 0, visited);
    }
    public int binarySearch(int [] nums, int start, boolean [] visited) {
        Queue<Stone> q = new LinkedList<>();
        visited[start] = true;
        q.offer(new Stone(nums[start], 1));

        while (q.isEmpty() == false) {
            Stone curr = q.poll();

            int back = curr.idx - nums[curr.idx];
            int forward = curr.idx + nums[curr.idx];

            if (forward >= nums.length - 1) return curr.jump + 1;

            if (!visited[forward] && nums[forward] > 0) {
                q.offer(new Stone(forward, curr.jump + 1));
                visited[forward] = true;
            }
            if (back > 0 && !visited[back] && nums[back] > 0) {
                q.offer(new Stone(back, curr.jump + 1));
                visited[back] = true;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        S3 s1 = new S3();
        int [][] nums = {
                {3, 1, 0, 2, 2, 1, 0, 4},
                {3, 1, 0, 2, 0, 1, 2, 4},
                {3, 1, 0, 2, 0, 2, 2, 4},
                {4, 1, 2, 3, 1, 0, 5},
                {5, 1, 0, 0, 2, 0, 1, 2, 3, 1, 0, 0, 2},
                {5, 2, 0, 2, 1, 1, 0, 1, 2, 1, 0, 0, 3},
                {5, 3, 0, 0, 4, 2, 0, 1, 0, 2, 2, 0, 3, 1},
                {5, 2, 0, 0, 0, 4, 0, 1, 2, 2, 1, 2, 3, 1}
        };
        int [] ans = {-1, 4, 3, 3, -1, -1, -1, 4};
        int cnt = 0, idx = 0;
        for (int [] num : nums) {
            // -1 , 4, 3, 3
            int answer = s1.solution(num);
            System.out.println(answer + " 정답: " + (ans[idx] == answer));
            if (ans[idx] == answer)
                cnt++;
            idx++;
        }
        System.out.println("cnt: " + cnt);
    }
}
