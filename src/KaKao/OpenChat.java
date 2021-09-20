package KaKao;

import java.util.*;

public class OpenChat {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        int len = record.length;
        int cnt = 0;
        for(int i = 0; i < len; i++) {
            String [] now_record = record[i].split(" ");

            if(now_record[0].equals("Leave"))
                continue;

            map.put(now_record[1], now_record[2]);
        }

        List<String> list = new ArrayList<>();

        for(int i = 0; i < len; i++) {
            String [] now_record = record[i].split(" ");
            if(now_record[0].equals("Enter"))
                list.add(map.get(now_record[1]) + "님이 들어왔습니다.");
            else if(now_record[0].equals("Leave"))
                list.add(map.get(now_record[1]) + "님이 나갔습니다.");
        }
        len = list.size();
        String[] answer = new String[len];

        for(int i = 0; i < len; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        OpenChat oc = new OpenChat();
        String [] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        System.out.println(Arrays.toString(oc.solution(record)));
    }
}
