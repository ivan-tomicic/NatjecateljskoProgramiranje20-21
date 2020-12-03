package Homework.DZ02.Solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class SortedAdjacentDifferences {

    static int N;
    static ArrayList<Integer> array;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        for(int i = 0; i < N; i++) {
            int A = sc.nextInt();
            array = new ArrayList<>(A);
            for(int j = 0; j < A; j++) {
                array.add(sc.nextInt());
            }
            array.sort(Comparator.comparingInt(a -> a));

            int leftIndex = A/2 - 1;
            int rightIndex = A/2;


            while(leftIndex >= 0 && rightIndex <= A - 1) {
                System.out.print(array.get(rightIndex) + " " + array.get(leftIndex) + " ");
                rightIndex++;
                leftIndex--;
            }
            if(rightIndex == A - 1) {
                System.out.print(array.get(A - 1) + " ");
            }
            System.out.println();
        }
    }
}
