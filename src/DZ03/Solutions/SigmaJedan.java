package DZ03.Solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SigmaJedan {

    final static int maxSum = 4390848;
    final static int maxN = 1000000;
    static int[] sieve = new int[maxN + 1];
    static int q;

    static HashMap<List<String>, Integer> tree = new HashMap<>();
    static int[][] queries = new int[100000][4];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 2; i <= maxN; i++) {
            for (int u = i; u <= maxN; u += i) {
                sieve[u] += i;
            }
        }

        q = Integer.parseInt(br.readLine());
        for(int i = 0; i < q; i++) {
            String[] params = br.readLine().split("\\s+");
            int a = Integer.parseInt(params[0]);
            int b = Integer.parseInt(params[1]);
            long u = Long.parseLong(params[2]);
            long v = Long.parseLong(params[3]);
            if(u > (long)Integer.MAX_VALUE + 1) {
                u = maxSum;
            }
            if(v > (long)Integer.MAX_VALUE + 1) {
                v = maxSum;
            }
            queries[i][0] = a;
            queries[i][1] = b;
            queries[i][2] = (int)u;
            queries[i][3] = (int)v;

            tree.put(Collections.unmodifiableList(Arrays.asList(Integer.toString(b),Integer.toString((int)v))), 0);
            tree.put(Collections.unmodifiableList(Arrays.asList(Integer.toString(b),Integer.toString((int)u-1))), 0);
            tree.put(Collections.unmodifiableList(Arrays.asList(Integer.toString(a-1),Integer.toString((int) v))), 0);
            tree.put(Collections.unmodifiableList(Arrays.asList(Integer.toString(a-1),Integer.toString((int)u-1))), 0);
        }

        for(List list : tree.keySet()) {
            tree.put(list, calculateSum(Integer.parseInt((String) list.get(0)), Integer.parseInt((String) list.get(1))));
        }

        // a b u v
        // b,v  -  b,u-1  -  a-1,v  -  a-1,u-1
        //
        //
        for(int i = 0; i < q; i++) {
            int first = tree.get(Arrays.asList(Integer.toString(queries[i][1]),Integer.toString(queries[i][3])));
            int second = tree.get(Arrays.asList(Integer.toString(queries[i][1]),Integer.toString(queries[i][2] - 1)));
            int third = tree.get(Arrays.asList(Integer.toString(queries[i][0] - 1),Integer.toString(queries[i][3])));
            int fourth = tree.get(Arrays.asList(Integer.toString(queries[i][0] - 1),Integer.toString(queries[i][2] - 1)));

            /*
            System.out.println("first: " + first);
            System.out.println("second: " + second);
            System.out.println("third: " + third);
            System.out.println("fourth: " + fourth);

            System.out.println("query1: " + queries[i][1] + " to " + queries[i][3]);
            System.out.println("query2: " + queries[i][1] + " to " + (queries[i][2] - 1));
            System.out.println("query3: " + (queries[i][0] - 1) + " to " + queries[i][3]);
            System.out.println("query4: " + (queries[i][0] - 1) + " to " + (queries[i][2] - 1));
            */
            System.out.println(first + fourth - second - third);
        }
    }

    public static int calculateSumOfDivisors(int i) {
        if (i == 1) return 1;
        return sieve[i] + 1;
    }

    public static int calculateSum(int maxNumber, int maxSum) {
        int sum = 0;
        int rightLimit = Math.min(maxNumber, maxSum);
        for(int i = 1; i <= rightLimit; i++) {
            if (calculateSumOfDivisors(i) <= maxSum) {
                sum++;
            }
        }
        return sum;
    }



}
