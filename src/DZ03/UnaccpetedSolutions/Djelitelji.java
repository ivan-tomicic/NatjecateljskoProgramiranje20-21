package DZ03.UnaccpetedSolutions;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

//This solution is too slow :/
public class Djelitelji {

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
            sieve[i] = i;
        }


        for(int i = 1; i <= maxN; i++) {
            int numberOfDivisors = calculateNumberOfDivisors(i);
            if(divisors.get(numberOfDivisors) == null) {
                divisors.put(numberOfDivisors, new ArrayList<>(Arrays.asList(i)));
            } else {
                divisors.get(numberOfDivisors).add(i);
            }
        }

        for(int i = 1; i <= q; i++) {
            int l = sc.nextInt();
            int q = sc.nextInt();
            int numberOfDivisors = sc.nextInt();
            int sum = 0;
            for(int j : divisors.get(numberOfDivisors)) {
                if (j > q) break;
                if( j >= l) sum++;
            }
            System.out.println(sum + " ");
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
