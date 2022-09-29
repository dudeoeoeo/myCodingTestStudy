package co;

import java.util.*;

public class So1 {

    boolean [] visited;
    int [] temp;
    int K;
    Map<Integer, ArrayList<Course>> treeMap = new HashMap<>();

    class Course {
        int num, time, point;

        public Course (int num, int time, int point) {
            this.num = num;
            this.time = time;
            this.point = point;
        }
    }

    public int[] solution(int n, int k, int[][] paths) {
        K = k;
        for (int i = 1; i <= n; i++) {
            treeMap.put(i, new ArrayList<>());
        }

        for (int i = 0; i < paths.length; i++) {
            int [] path = paths[i];
            treeMap.get(path[0]).add(new Course(path[1], path[2], path[3]));

            if (path[0] != 1 && path[1] != k)
                treeMap.get(path[1]).add(new Course(path[0], path[2], path[3]));
        }

        List<int []> results = new ArrayList<>();

        for (int i = 0; i < treeMap.get(1).size(); i++) {
            visited = new boolean[n + 1];
            temp = new int[2];
            final Course course = treeMap.get(1).get(i);

            if (course.num == k) {
                results.add(new int[]{course.time, course.point});
                continue;
            }
            dfs(course);
            results.add(temp);
        }

        int[] answer = results.get(0);

        for (int i = 1; i < results.size(); i++) {
            final int[] arr = results.get(i);
            if (answer[0] >= arr[0]) {
                if (answer[1] <= arr[1])
                    answer = arr;
            }
        }
        return answer;
    }

    void dfs(Course course) {
        if (visited[course.num] == true) return;
        visited[course.num] = true;
        temp[0] += course.time;
        temp[1] += course.point;
        if (course.num == K) return;

        for (int i = 0; i < treeMap.get(course.num).size(); i++) {
            dfs(treeMap.get(course.num).get(i));
        }
    }

    public static void main(String[] args) {
        int n = 5, k = 4;
        int [][] paths = {{1,5,1,1}, {1,2,4,3}, {1,3,3,2}, {2,5,2,1}, {2,4,2,3}, {3,4,2,2}};

        So1 s1 = new So1();
        System.out.println(Arrays.toString(s1.solution(n,k,paths)));
    }
}
