package Anipointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {
    String [] cards;
    public String[] solution(String[] card, String[] word) {

        cards = card;
        List<String> list = new ArrayList<>();

        for(int i = 0; i < word.length; i++) {
            boolean [] line = new boolean[3];
            String [] now_word = word[i].split("");
            if(makeWord(now_word, line))
                list.add(word[i]);
        }
        String[] answer = new String[list.size()];
        if(answer.length == 0) {
            answer = new String[1];
            answer[0] = "-1";
        } else {
            for(int i = 0; i < list.size(); i++)
                answer[i] = list.get(i);
        }
        return answer;
    }
    public boolean makeWord(String [] word, boolean [] line) {
        StringBuilder sb = new StringBuilder();
        boolean [] word_bool = new boolean[word.length];
        for(int i = 0; i < cards.length; i++) {

            for(int j = 0; j < word.length; j++) {
                if(cards[i].contains(word[j])) {
                    line[i] = true;
                    word_bool[j] = true;
                }
            }
            if(!line[i]) return false;
        }
        if(Arrays.toString(word_bool).contains("false"))
            return false;

        return true;
    }

    public static void main(String[] args) {
        Solution2 s2 = new Solution2();
        String [] card = {"ABACDEFG", "NOPQRSTU", "HIJKLKMM"}, word = {"GPQM", "GPMZ", "EFU", "MMNA"};
        System.out.println(Arrays.toString(s2.solution(card,word)));
    }
}
