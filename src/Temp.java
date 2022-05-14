import java.util.HashMap;

public class Temp {
    public int solution(int n, int[] lost, int[] reserve) {
        HashMap<Integer, Integer> lostMap = new HashMap<>();
        for(int i : lost) {
            lostMap.put(i, i);
        }
        for(int i : reserve) {
            if(lostMap.containsKey(i))
                lostMap.remove(i);
        }
        for(int i=0; i<reserve.length; i++) {
            int key = reserve[i];

            if(lostMap.containsKey(key-1)) {
                lostMap.remove(key-1);
            }
            if(lostMap.containsKey(key+1)) {
                lostMap.remove(key+1);
            }
        }
        int answer = n - lostMap.size();

        return answer;
    }

    public static void main(String[] args) {
        Temp t = new Temp();
        int n = 5;
        int [] lost = {2, 4};
        int [] reserve = {1, 3, 5};
        System.out.println(t.solution(n,lost,reserve));

        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        s = s.replaceAll("\\{", "").replaceAll("\\}", "");
        System.out.println(s);
    }
}
