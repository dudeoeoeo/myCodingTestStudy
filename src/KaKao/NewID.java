package KaKao;

import java.util.Arrays;
import java.util.Locale;

public class NewID {
    StringBuilder sb = new StringBuilder();
    String match = "~!@#$%^&*()=+[{]}:?,<>/|\\";
    public String solution(String new_id) {
        checkWord(new_id);
        removeEndPoint(sb.toString());
        remove(sb.toString());
        setLengthId(sb.toString());
        checkLength(sb.toString());
        return sb.toString();
    }
    void checkWord(String id) {
        char [] arr = id.toCharArray();
        for(char word : arr) {
            System.out.println("word: "+word);
            if(!match.contains(String.valueOf(word)))
                sb.append(word);
        }
    }
    void removeEndPoint(String id) {
        System.out.println("id : "+id);
        if(id.startsWith(".")) {
            String temp = id.substring(1, id.length());
            removeEndPoint(temp);
        } else if(id.charAt(id.length()-1) == '.') {
            System.out.println("else if: "+ id.charAt(id.length()-1));
            String temp = id.substring(0, id.length() - 1);
            removeEndPoint(temp);
        } else {
            sb.setLength(0);
            sb.append(id);
        }
    }
    void remove(String id) {
        if(id.contains(".")) {
            StringBuilder sbTemp = new StringBuilder();
            char[] arr = id.toCharArray();
            char pre = arr[0];
            for(int i = 1; i < arr.length; i++) {
                char now = arr[i];
                if(!(pre == '.' && now == '.'))
                    sbTemp.append(pre);
                pre = now;
            }
            sb.setLength(0);
            sb.append(sbTemp);
            System.out.println("sb 는 : "+sb.toString());
        }
    }
    void setLengthId(String id) {
        if(id.length() >= 15) {
            System.out.println("len: "+id.length());
            sb.setLength(15);
            System.out.println("sb: "+sb.toString());
            removeEndPoint(sb.toString());
        }
    }
    void checkLength(String id) {
        if(id.length() == 0) {
            sb.append("aaa");
        } else if(id.length() < 3) {
            char word = sb.charAt(sb.length() - 1);
            while (sb.length() < 3)
                sb.append(word);
        }
    }
    public static void main(String[] args) {
        NewID n = new NewID();
        String id = "...!@BaT#*..y.abcdefghijklm";
        System.out.println(n.solution(id));
    }
}
