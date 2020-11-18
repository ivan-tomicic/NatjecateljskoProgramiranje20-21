package DZ03.Solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Djelitelji {

    final static int maxN = 100000;
    static int[][] prefixArray = new int[129][maxN + 1];

    static int[] sieve = new int[maxN + 1];
    static int q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        q = Integer.parseInt(br.readLine());

        for (int i = 2; i <= maxN; i++) {
            for (int u = 2*i; u <= maxN; u += i) {
                sieve[u]++;
            }
        }
        for(int i = 1; i <= maxN; i++) {
            int numberOfDivisors = calculateNumberOfDivisors(i);
            prefixArray[numberOfDivisors][i] = 1;
        }

        for(int j = 1; j <= 128; j++) {
            for(int i = 1; i <= maxN; i++) {
                prefixArray[j][i] += prefixArray[j][i - 1];
            }
        }

        for(int i = 1; i <= q; i++) {
            String[] params = br.readLine().split("\\s+");
            int l = Integer.parseInt(params[0]);
            int r = Integer.parseInt(params[1]);
            int k = Integer.parseInt(params[2]);
            if(k > 128 ) {
                System.out.println(0);
            } else {
                System.out.println(prefixArray[k][r] - prefixArray[k][l - 1]);
            }

        }
    }

    public static int calculateNumberOfDivisors(int i) {
        if (i == 1) return 1;
        return sieve[i] + 2;
    }


}