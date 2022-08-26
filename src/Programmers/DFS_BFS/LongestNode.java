package Programmers.DFS_BFS;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class LongestNode {
    Map<Integer, ArrayList<Integer>> map = new HashMap<>();
    int [] nodes;
    boolean [] visited;

    class Node {
        int num, distance;
        public Node(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }
    }

    public int solution(int n, int[][] edge) {
        nodes = new int[n + 1];
        for (int i = 0; i < edge.length; i++) {
            int one = edge[i][0];
            int two = edge[i][1];

            if (map.containsKey(one) == false) {
                map.put(one, new ArrayList<>());
                map.get(one).add(two);
            } else {
                if (map.get(one).contains(two) == false) {
                    map.get(one).add(two);
                }
            }
            if (map.containsKey(two) == false) {
                map.put(two, new ArrayList<>());
                map.get(two).add(one);
            } else {
                if (map.get(two).contains(one) == false) {
                    map.get(two).add(one);
                }
            }
        }
        // 1번 노드가 가지고 있는 자식들을 차례로 순회
        // 최대 간선 수 저장, 최대 간선과 같은 간선의 수를 가진 자식 노드 개수 저장
        // 만약 새로운 간선 최대 수가 나오면 최대 간선의 수를 가진 자식 노드 개수 새로 갱신
        visited = new boolean[n + 1];
        visited[1] = true;

        for (int i = 0; i < map.get(1).size(); i++) {
            int nowNode = map.get(1).get(i);
            bfs(nowNode, n);
        }

        int max = 0, answer = 0;

        for (int i = 2; i < n + 1; i++)
            max = Math.max(nodes[i], max);

        for (int i = 2; i < n + 1; i++) {
            if (max == nodes[i])
                answer++;
        }

        return answer;
    }

    void bfs(int s_node, int n) {
        boolean [] inVisited = new boolean[n + 1];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(s_node, 1));

        inVisited[1] = true;
        inVisited[s_node] = true;
        visited[s_node] = true;
        nodes[s_node] = 1;

        while (q.isEmpty() == false) {
            Node curr = q.poll();

            for (int i = 0; i < map.get(curr.num).size(); i++) {
                int nowNode = map.get(curr.num).get(i);

                if (visited[nowNode] && inVisited[nowNode]) continue;
                if (map.get(1).contains(nowNode)) continue; // root와 연결된 노드면 종료

                // 전체 노드에서는 방문한 노드고 안에서는 방문을 안 했다면 값 비교
                if (visited[nowNode] && !inVisited[nowNode]) {
                    int min = Math.min(nodes[nowNode], curr.distance + 1);
                    nodes[nowNode] = min;
                    inVisited[nowNode] = true;
                    q.offer(new Node(nowNode, min));
                    continue;
                }

                visited[nowNode] = true;
                inVisited[nowNode] = true;
                nodes[nowNode] = curr.distance + 1;
                q.offer(new Node(nowNode, curr.distance + 1));
            }
        }
    }
}
