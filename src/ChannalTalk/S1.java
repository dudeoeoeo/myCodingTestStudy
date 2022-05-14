package ChannalTalk;

public class S1 {
    public int solution(int[][] locations) {
        int answer = 0;
        for(int i = 0; i < locations.length; i++) {
            int cnt = 0;
            for(int j = 0; j < 2; j++) {
                if(locations[i][j] == 2)
                    cnt++;
            }
            if(cnt == 2)
                return -1;
        }
        answer = locations.length;
        return answer;
    }
}
