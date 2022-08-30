package Programmers.DFS_BFS;

public class ToothBrush {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
        int[] answer = new int[N];
        String parent;
        int parentPrice = 0, tmp = 0;

        for (int i = 0; i < seller.length; i++) {
            for (int j = 0; j < N; j++) {
                if(seller[i].equals(enroll[j])) {
                    answer[j] += 90 * amount[i];
                    parentPrice = 10 * amount[i];
                    parent = referral[j];

                    loop:
                    while(!parent.equals("-")) {
                        for (int j2 = 0; j2 < N; j2++) {
                            if(enroll[j2].equals(parent)){
                                parent  = referral[j2];

                                tmp = parentPrice;
                                parentPrice = (int) (parentPrice * 0.1);
                                answer[j2] += tmp - parentPrice;
                                break;
                            }
                        }
                    }

                    break;
                }
            }
        }
        return answer;
    }
}
