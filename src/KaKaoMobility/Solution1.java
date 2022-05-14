package KaKaoMobility;

import java.util.*;

public class Solution1 {
    String company;
    public String solution(String S, String C) {

        company = C;
        String [] names = S.split(",");
        Map<String, Integer> dup_map = new HashMap<>();
        List<String> list = new ArrayList<>();

        for(int i = 0; i < names.length; i++) {
            String [] temp = names[i].trim().split(" ");

            String first_name = toLowerFirstAndMiddle(temp);

            String middle_name = toLowerCaseStr(temp[temp.length - 1]);

            String email = toEmail(names[i], first_name, middle_name);

            if(dup_map.containsKey(email)) {
                dup_map.put(email, dup_map.get(email) + 1);
                email = replaceEmail(email, dup_map.get(email));
            }
            dup_map.put(email, 1);
            list.add(names[i] + email);
        }
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            if(i < list.size() - 1)
                answer.append(list.get(i) + ",");
            else
                answer.append(list.get(i));
        }
        return answer.toString();
    }

    public String toLowerFirstAndMiddle(String [] names) {
        String name = "";
        for(int i = 0; i < names.length - 1; i++) {
            if(names[i].equals(",") || names[i].equals(" ")) continue;
            name += String.valueOf(names[i].trim().charAt(0)).toLowerCase(Locale.ROOT);
        }
        return name;
    }

    public String toLowerCaseStr(String middle) {
        middle = middle.replaceAll("-", "");
        String middle_name = "";
        int len = middle.length() > 8 ? 8 : middle.length();
        for(int i = 0; i < len; i++) {
            middle_name += String.valueOf(middle.charAt(i)).toLowerCase(Locale.ROOT);
        }
        return middle_name;
    }

    public String toEmail(String name, String first, String middle) {
        String email = " <" + first + middle + "@" + company + ".com>";
        return email;
    }

    public String replaceEmail(String email, int dup) {
        String [] temp = email.split("@");
        String rename = temp[0] + dup + "@" + temp[1];
        return rename;
    }

    public static void main(String[] args) {
        String s = "John Doe, Peter Parker, Mary Jane Watson-Parker, James Doe, John Elvis Doe, Jane Doe, Penny Parker";
        String ss = "John Doe, Peter Parker, Mary Jane Watson-Parker, James Doe, John Elvis Doe, Jane Doe, Penny Parker";
        String c = "example";
        Solution1 s1 = new Solution1();
        System.out.println("end :"+s1.solution(ss, c));
    }
}
