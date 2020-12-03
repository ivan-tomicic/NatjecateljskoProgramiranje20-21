package Homework.DZ03.Solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class T_Primes {

    final static int maxN = 1000000;
    static boolean[] sieve = new boolean[maxN + 1];

    public static void main(String[] args) throws IOException {

        Arrays.fill(sieve, Boolean.TRUE);

        sieve[0] = sieve[1] = false;
        for(int i = 2; i*i < maxN; i++) {
            if(sieve[i]) {
                for(int j = i; i*j <= maxN; j++)
                {
                    sieve[i*j]=false;
                }
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputSize = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split("\\s+");

        for(int i = 0; i < inputSize; i++) {
            double number = Math.sqrt(Long.parseLong(input[i]));
            if(number % 1 == 0 && sieve[(int)Math.round(number)] == true) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }



}
