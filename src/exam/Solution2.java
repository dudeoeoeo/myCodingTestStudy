package exam;

import java.util.*;

public class Solution2 {
    public String[] solution(String[] logs) {
        int [][] peoples = new int[5001][5001];
        Map<String, List<String>> map = new HashMap<>();

        for (String log : logs) {
            String[] split = log.split(" ");
            String key = split[0];
            String value = split[0] + " " + split[1];
            int people = Integer.parseInt(split[0]);
            int p_num = Integer.parseInt(split[1]);
            int score = Integer.parseInt(split[2]);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>(Arrays.asList(value)));
                peoples[people][p_num] = score;
            } else {
                if (map.get(key).contains(value) == false) {
                    map.get(key).add(value);
                    peoples[people][p_num] = score;
                } else {
                    int savedScore = peoples[people][p_num];
                    if (savedScore < score)
                        peoples[people][p_num] = score;
                }
            }
        }
        Map<Integer, List<String>> sameCor = new HashMap<>();
        for (String key : map.keySet()) {
            if (map.get(key).size() < 5) continue;

            if (sameCor.containsKey(map.get(key).size()) == false) {
                sameCor.put(map.get(key).size(), new ArrayList<>(Arrays.asList(key)));
            } else {
                sameCor.get(map.get(key).size()).add(key);
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean result = false;
        for (int key : sameCor.keySet()) {
            List<String> persons = sameCor.get(key);

            for (int i = 0; i < persons.size(); i++) {
                int p1 = Integer.parseInt(persons.get(i));
                List<String> examList1 = map.get(persons.get(i));
                for (int j = i + 1; j < persons.size(); j++) {
                    int p2 = Integer.parseInt(persons.get(j));
                    List<String> examList2 = map.get(persons.get(j));
                    boolean check = true;
                    for (int k = 0; k < examList1.size(); k++) {
                        String num1 = examList1.get(k).split(" ")[1];
                        String num2 = examList2.get(k).split(" ")[1];

                        if (peoples[p1][Integer.parseInt(num1)] != peoples[p2][Integer.parseInt(num1)]) {
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        result = true;
                        if (sb.indexOf(persons.get(i)) <= -1)
                            sb.append(persons.get(i) + " ");
                        if (sb.indexOf(persons.get(j)) <= -1)
                            sb.append(persons.get(j) + " ");
                    }
                }
            }
        }
        if (result == false) return new String[]{"None"};
        String[] answer = sb.toString().split(" ");
        Arrays.sort(answer);
        return answer;

    }

    public static void main(String[] args) {
        String [] p = {"0001 3 95", "0001 5 90", "0001 5 100", "0002 3 95", "0001 7 80", "0001 8 80", "0001 10 90", "0002 10 90", "0002 7 80", "0002 8 80", "0002 5 100", "0003 99 90"};
        Solution2 s2 = new Solution2();
        System.out.println(Arrays.toString(s2.solution(p)));
    }
    /**
     *  Map<String, List<String>> map = new HashMap<>();
     *         Map<String, Integer> peopleMap = new HashMap<>();
     *         int [][] check_arr = new int[5001][5001];
     *
     *         // 수험자가 같은 번호의 답을 다르게 적어냈을경우 5번 문제가 2개 일 경우
     *         for (String log : logs) {
     *             String num = log.substring(0, 4); // 수험번호
     *             String [] exam = log.substring(5, log.length()).split(" ");
     *             int p = Integer.parseInt(num);
     *             int e1 = Integer.parseInt(exam[0]); // 문제 번호
     *             int score = Integer.parseInt(exam[1]); // 점수
     *
     *             int savedScore = check_arr[p][e1];
     *             List<String> list = map.getOrDefault(num, new ArrayList<>());
     *
     *             if (savedScore != 0) {
     *                 list.remove(exam[0] + " " + exam[1]);
     *                 if (savedScore > score) {
     *                     score = savedScore;
     *                 } else {
     *                     check_arr[p][e1] = score;
     *                 }
     *             } else {
     *                 check_arr[p][e1] = score;
     *             }
     *
     *             list.add(exam[0] + " " + score);
     *             map.put(num, list);
     *
     *             peopleMap.put(num, peopleMap.getOrDefault(num, 0) + 1);
     *         }
     *
     *         Map<Integer, List<String>> sameList = new HashMap<>();
     *         int cnt = 0;
     *         for (String key : peopleMap.keySet()) {
     *             int submit = peopleMap.get(key);
     *             if (submit < 5)
     *                 continue;
     *             cnt++;
     *             List<String> list = sameList.getOrDefault(submit, new ArrayList<>());
     *             list.add(key);
     *             sameList.put(submit, list);
     *         }
     *         System.out.println(map.toString());
     *         System.out.println(peopleMap.toString());
     *         System.out.println(sameList.toString() + ", size: " + sameList.size());
     *         if (cnt < 2) return new String[]{"None"};
     *
     *         StringBuilder sb = new StringBuilder();
     *
     *         for (int key : sameList.keySet()) {
     *             List<String> peopleList = sameList.get(key);
     *             System.out.println("peopleList: " + peopleList.toString());
     *             for (int i = 0; i < peopleList.size(); i++) {
     *                 String p1 = peopleList.get(i);
     *                 for (int j = i + 1; j < peopleList.size(); j++) {
     *                     String p2 = peopleList.get(j);
     *                     boolean check = true;
     *                     List<String> answer1 = map.get(p1);// p1 이 적어낸 답지
     *                     List<String> answer2 = map.get(p2); // p2 가 적어낸 답지
     *                     for (int k = 0; k < answer1.size(); k++) {
     *                         System.out.println("answer: " + answer1.get(k) +", " + answer2.get(k));
     *                         if (answer1.contains(answer2.get(k)) == false) {
     *                             check = false;
     *                             break;
     *                         }
     *                     }
     *                     if (check) {
     *                         if (sb.indexOf(p1) <= -1)
     *                             sb.append(p1 + " ");
     *                         if (sb.indexOf(p2) <= -1)
     *                             sb.append(p2 + " ");
     *                     }
     *                 }
     *             }
     *         }
     *
     */
}
