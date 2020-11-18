package DZ03.UnaccpetedSolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class proba {

    final static int maxSum = 4390848;
    final static int maxN = 1000000;
    static int[] sieve = new int[maxN + 1];

    static TreeMap<Integer, Integer> tree = new TreeMap<>();


    static ArrayList<ArrayList<Integer>> segmentTree;
    static int nextPowerOfTwo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nextPowerOfTwo = (int) Math.pow(2, Math.ceil(Math.log10(maxN)/Math.log10(2)));
        segmentTree = new ArrayList<>();
        for(int i = 0; i <= nextPowerOfTwo*2; i++) {
            segmentTree.add(i, new ArrayList(Arrays.asList()));
        }

        for (int i = 2; i <= maxN; i++) {
            for (int u = i; u <= maxN; u += i) {
                sieve[u] += i;
            }

        }

        for(int i = 1; i <= maxN; i++) {
            int calc = calculateSumOfDivisors(i);
            if(tree.get(calc) == null) {
                tree.put(calc, 1);
            } else {
                tree.put(calc, tree.get(calc) + 1);
            }
        }

        int q = Integer.parseInt(br.readLine());
        for(int i = 0; i < q; i++) {
            String[] params = br.readLine().split("\\s+");
            int a = Integer.parseInt(params[0]);
            int b = Integer.parseInt(params[1]);
            long u = Long.parseLong(params[2]);
            long v = Long.parseLong(params[3]);
            if(u > Integer.MAX_VALUE + 1) {
                u = maxSum;
            }
            if(v > Integer.MAX_VALUE + 1) {
                v = maxSum;
            }
        }

    }

    public static int calculateSumOfDivisors(int i) {
        if (i == 1) return 1;
        return sieve[i] + 1;
    }


}
