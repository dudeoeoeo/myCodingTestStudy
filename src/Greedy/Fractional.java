package Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Fractional {
    public static void main(String[] args) {
        int [][] objectList = {{25, 8}, {10, 10}, {20, 10}, {15, 12}, {30, 5}};
        double capacity = 30.0;
        Fractional f = new Fractional();
        f.fractionalKnapsack(objectList, capacity);
        /*
        System.out.println((8 / (double)(25/10)));
        System.out.println((10 / (double)(10/10)));
        System.out.println((10 / (double)(20/10)));
        System.out.println((12 / (double)(15/10)));
        System.out.println((12 / 1.5));
        System.out.println((5 / (double)(30/10)));

         */
    }
    public double fractionalKnapsack(int [][] objectList, double capacity) {
        double totalValue = 0.0;
        double fraction = 0.0;

        Arrays.sort(objectList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //System.out.printf("o1[0]: %d, o1[1]: %d, o2[0]: %d, o2[1]: %d \n",o1[0],o1[1],o2[0],o2[1]);
                //System.out.printf("(o2[1] / o2[0]) %d - (o1[1] / o1[0]) %d = %d \n",(o2[1] / o2[0]), (o1[1] / o1[0]), (o2[1] / o2[0]) - (o1[1] / o1[0]));
                System.out.println((double) (o1[1] / o1[0]));
                return (int) ((int) (o2[1] / (double)(o2[0] / 10)) - (o1[1] / (double)(o1[0] / 10)));
            }
        });
        System.out.println("==================================");
        for(int [] now : objectList) {
            System.out.println(Arrays.toString(now));
        }
        for(int i = 0; i < objectList.length; i++) {

            if((capacity - (double)objectList[i][0]) > 0) {
                totalValue = (double)objectList[i][1];
                capacity -= (double)objectList[i][0];
                System.out.println("무게: "+objectList[i][0]+", 가치: "+objectList[i][1]);
            } else {
                fraction = capacity / (double)objectList[i][0];
                totalValue += (double) objectList[i][1] * fraction;
                System.out.println("무게: "+objectList[i][0]+", 가치: "+objectList[i][1]+", 비율: "+fraction);
                break;
            }
        }
        System.out.println(totalValue);
        return totalValue;
    }
}
