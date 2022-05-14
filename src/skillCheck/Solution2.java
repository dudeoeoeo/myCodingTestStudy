package skillCheck;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution2 {
    Stack<Integer> stack = new Stack<>();
    String [] OX_arr;
    int row;

    public String solution(int n, int k, String[] cmd) {
        System.out.println(Arrays.toString(cmd));
        String answer = "";
        row = n;

        for(int i = 0; i < cmd.length; i++) {
            if(cmd[i].length() == 1)
                k = stackCom(cmd[i], k);
            else
                k = command(cmd[i], k);
        }

        OX_arr = new String[n];
        Arrays.fill(OX_arr, "O");
        System.out.println(stack.toString());
        int size = stack.size();
        for(int i = 0; i < size; i++)
            OX_arr[stack.pop()] = "X";

        return String.join("", OX_arr);
    }
    int stackCom(String command, int k) {
        System.out.println("command: "+command+", k: "+k);
        System.out.println("stack: "+stack.toString());
        if(command.equals("C")) {
            stack.add(k);
            //int now_size = row - stack.size();
            //return now_size == k ? k - 1 : k;
            return row == k ? k - 1 : k;
        }
        stack.pop();
        return k;
    }
    int command(String cmd, int k) {
        System.out.println("cmd U or D: "+cmd+", k : "+k);
        return cmd.split(" ")[0].equals("U") ? k - Integer.valueOf(cmd.split(" ")[1]) : k + Integer.valueOf(cmd.split(" ")[1]);
    }

    public static void main(String[] args) {
        int n = 8, k = 2;
        String [] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        String [] cmd1 = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        Solution2 s2 = new Solution2();
        System.out.println(s2.solution(n,k,cmd1));
        //8	2	["D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"]	"OOXOXOOO"
    }
}
