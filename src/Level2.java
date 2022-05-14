import java.util.*;

public class Level2 {

    class Tuple {
        int cnt;
        int num;

        public Tuple(int cnt, int num) {
            this.cnt = cnt;
            this.num = num;
        }
    }
    Comparator<Tuple> comp = new Comparator<Tuple>() {

        public int compare(Tuple t1, Tuple t2) {
            if(t1.cnt < t2.cnt) {
                return 1;
            } else if(t1.cnt > t2.cnt) {
                return -1;
            } else
                return 0;
        }
    };

    public int [] solution(String s) {
        String [] tuples = s.replaceAll("\\{", "").replaceAll("\\}", "").split(",");
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Tuple> list = new ArrayList<>();

        for(String tuple : tuples) {
            int num = Integer.parseInt(tuple);
            if(!map.containsKey(num))
                map.put(num, 1);
            else
                map.put(num, map.get(num) + 1);
        }
        for(int key : map.keySet()) {
            list.add(new Tuple(map.get(key), key)); // cnt, num
        }
        Collections.sort(list, comp);

        int [] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).num);
           answer[i] = list.get(i).num;
        }
        return answer;
    }

    public static void main(String[] args) {
        Level2 l2 = new Level2();
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        l2.solution(s);
    }
}
