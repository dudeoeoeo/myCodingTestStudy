package level1;

import java.util.Arrays;
import java.util.OptionalInt;

public class NumberPlus {
    public int solution(int[] numbers) {
        return 45 - sumNum(numbers);
    }

    int sumNum(int [] numbers) {
        int sum = 0;
        for(int num : numbers) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        int [] n = {1,4,2,8,5,9};
        NumberPlus nn = new NumberPlus();
        System.out.println(nn.solution(n));
    }
}
