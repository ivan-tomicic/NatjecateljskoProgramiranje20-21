package Homework.DZ04.Solutions;

import java.util.*;

public class EventDates {

    public static void main(String[] args) {
        boolean[] takenDates = new boolean[10000001];

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] solutions = new int[n + 1];

        int[][] array = new int[n][3];
        for(int i = 0; i < n; i++) {
            array[i][0] = i;
            array[i][1] = sc.nextInt();;
            array[i][2] = sc.nextInt();;

        }
        Arrays.sort(array, Comparator.comparingDouble(o -> o[2]));

        for(int i = 0; i < n; i++) {
            int j = array[i][1];
            while(takenDates[j]) j++;
            takenDates[j] = true;
            solutions[array[i][0]] = j;
        }

        for(int i = 0; i < n; i++) {
            System.out.print(solutions[i] + " ");
        }

    }
}
