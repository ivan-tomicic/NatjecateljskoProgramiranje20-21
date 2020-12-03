package Homework.DZ03.UnaccpetedSolutions;

import java.util.*;

public class Kasino {
    final static long modulo = 1000000007L;
    static int n;
    static int m;
    static long t;

    static LinkedHashMap<Integer, ArrayList<Integer>> respectedBy; //key respected is respected by all the people in ints value

    static long[] money;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        money = new long[n + 1];
        respectedBy = new LinkedHashMap<>();

        for(int i = 1; i <= m; i++) {
            int respecter = sc.nextInt();
            int respected = sc.nextInt();

            if(respectedBy.get(respected) == null) {
                respectedBy.put(respected, new ArrayList<>(Arrays.asList(respecter)));
            } else {
                respectedBy.get(respected).add(respecter);
            }
        }

        for(int i = 1; i <= n; i++) {
            money[i] = sc.nextLong();
        }
        t = sc.nextLong();


        for(int j = 1; j <= n; j++) {
            System.out.print(calcucalateSum(j) + " ");
        }
    }

    static long modpow(long x, long n) {
        if (n == 0) return 1;
        long u = modpow(x,n/2);
        u = ((u%modulo)*(u%modulo))%modulo;
        if (n%2 == 1){
            u = ((u%modulo)*(x%modulo))%modulo;
        }
        return u;
    }

    static long calcucalateSum(int element) {
        ArrayList<Integer> respecters = new ArrayList<>(Arrays.asList(element));

        long cnt = 0;
        long moneyAfterT = 0;
        while (cnt <= t) {
            if (respecters == null) {
                return moneyAfterT % modulo;
            }
            long sum = 0;
            for(Integer respecter : respecters) {
                sum += money[respecter];
            }
            sum = (sum%modulo * binom(t, cnt)%modulo)%modulo;
            moneyAfterT = (moneyAfterT%modulo + sum%modulo)%modulo;

            cnt++;
            ArrayList<Integer> respectersTemp = new ArrayList<>(respecters);
            respecters.clear();

            for(int i : respectersTemp) {
                if(respectedBy.get(i) == null) continue;
                for(int j : respectedBy.get(i)) {
                    respecters.add(j);
                }
            }
        }
        return moneyAfterT;
    }

    static long binom(long a, long b) {
        return (factorial(a) / (factorial(b) * factorial(a - b))%modulo);
    }

    static long factorial(long a) {
        if (a>=1)
            return a*factorial(a-1);
        else
            return 1;
    }
}