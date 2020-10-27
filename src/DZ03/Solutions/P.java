package DZ03.Solutions;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;


public class P {

    static int q;
    final static int maxN = 100000;
    static int[] sieve = new int[maxN + 1];


    static TreeMap<Integer, ArrayList<Integer>> divisors = new TreeMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        q = sc.nextInt();

        for (int i = 2; i <= maxN; i++) {
            if (sieve[i] != 0) continue;
            for (int u = 2*i; u <= maxN; u += i) {
                sieve[u] = i;
            }

        }

        /*for(int i = 1; i <= 36; i++) {
            System.out.print(sieve[i] + " ");
        }
        System.out.println();*/


        for(int i = 1; i <= q; i++) {
            int l = sc.nextInt();
            int q = sc.nextInt();
            int numberOfDivisors = sc.nextInt();

            //int answer = calculateNumberOfNumbersWithDivisor(numberOfDivisors, q) - calculateNumberOfNumbersWithDivisor(numberOfDivisors, l);
            //System.out.println(answer);
        }

    }
    public static int calculateNumberOfDivisors(int i) {
        if (i == 1) return 1;
        if (sieve[i] == 0) return 2;

        int total = 1;

        while(i != 1) {
            int x = sieve[i];
            int count = 0;
            while(i % x == 0) {
                count++;
                i = i / x;
            }
            total = total * (count + 1);
        }
        return total;
    }



}
