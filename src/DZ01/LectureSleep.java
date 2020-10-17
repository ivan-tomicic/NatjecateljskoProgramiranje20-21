package DZ01;

import java.util.Scanner;

public class LectureSleep {

    static int N;
    static int K;
    static int[] theorems;
    static int[] MishkaStates;


    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        theorems = new int[N];
        MishkaStates = new int[N];

        int sumWhileAwake = 0;

        for(int i = 0; i < N; i++) {
            theorems[i] = sc.nextInt();
        }

        for(int i = 0; i < N; i++) {
            if(sc.nextInt() == 1) {
                sumWhileAwake += theorems[i];
                theorems[i] = 0;
            }
        }
        int maxSum = 0;
        for(int i = 0; i < K; i++) {
            maxSum += theorems[i];
        }

        int currentSum = maxSum;
        for(int i = K; i < N; i++) {
            currentSum += theorems[i] - theorems[i-K];
            maxSum = Math.max(currentSum, maxSum);
        }
        System.out.println(maxSum + sumWhileAwake);
    }
}
