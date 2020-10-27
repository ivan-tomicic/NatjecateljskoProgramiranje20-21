package DZ03.Solutions;

import java.util.*;

public class Kasino {
    static int n;
    static int m;
    static long t;
    static LinkedHashMap<Integer, ArrayList<Integer>> respects; //key respector respects all the people in its value
    static LinkedHashMap<Integer, ArrayList<Integer>> respectedBy; //key respected is respected by all the people in ints value

    static int[] moneyBefore;
    static int[] moneyAfter;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        respects = new LinkedHashMap<>();
        respectedBy = new LinkedHashMap<>();
        moneyBefore = new int[n + 1];
        moneyAfter = new int[n + 1];

        for(int i = 1; i <= m; i++) {
            int respector = sc.nextInt();
            int respected = sc.nextInt();
            if(respects.get(respector) == null) {
                respects.put(respector, new ArrayList<>(Arrays.asList(respected)));
                continue;
            }
            respects.get(respector).add(respected);
        }

        for(int i = 1; i <= n; i++) {
            int number = sc.nextInt();
            moneyBefore[i] = number;
            moneyAfter[i] = number;
        }
        t = sc.nextLong();
        t = t%1000000007;

        for(int i = 0; i < t; i++) {
            for(Integer respector : respects.keySet()) {
                for(Integer respected : respects.get(respector)) {
                    moneyAfter[respected] = moneyAfter[respected]%1000000007 + moneyBefore[respector]%1000000007;

                }
            }
            moneyBefore = moneyAfter.clone();
        }
        for(int j = 1; j <= n; j++) {
            System.out.print(moneyAfter[j] + " ");
        }

    }
    static int modpow(int x, long n, int m) {
        if (n == 0) return 1;
        int u = modpow(x,n/2, m);
        u = (u*u)%m;
        if (n%2 == 1){
            u = (u*x)%m;
        }
        return u;
    }
}
