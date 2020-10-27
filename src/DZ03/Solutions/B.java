package DZ03.Solutions;

import java.util.*;

public class B {
    final static long modulo = 1000000007L;
    static int n;
    static int m;
    static long t;
    static long multiplicationConstant;
    static LinkedHashMap<Integer, ArrayList<Integer>> respects; //key respecter respects all the people in its value
    static LinkedHashMap<Integer, ArrayList<Integer>> respectedBy; //key respected is respected by all the people in ints value

    static long[] moneyAfter2nDays;
    static long[] money;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        respects = new LinkedHashMap<>();
        respectedBy = new LinkedHashMap<>();

        moneyAfter2nDays = new long[n + 1];
        money = new long[n + 1];

        for(int i = 1; i <= m; i++) {
            int respecter = sc.nextInt();
            int respected = sc.nextInt();
            if(respects.get(respecter) == null) {
                respects.put(respecter, new ArrayList<>(Arrays.asList(respected)));
            } else {
                respects.get(respecter).add(respected);
            }

            if(respectedBy.get(respected) == null) {
                respectedBy.put(respected, new ArrayList<>(Arrays.asList(respecter)));
            } else {
                respectedBy.get(respected).add(respecter);
            }
        }
        for(int i = 1; i <= n; i++) {
            moneyAfter2nDays[i] = sc.nextLong();
        }
        t = sc.nextLong();

        money = moneyAfter2nDays.clone();



        long iterations = Math.min(n*n, t);
        for(int i = 1; i <= iterations; i++) {
            for(int j = 1; j <= n; j++) {
                if (respectedBy.get(j) != null) {
                    for(int respecter : respectedBy.get(j)) {
                        moneyAfter2nDays[j] = (moneyAfter2nDays[j]%modulo + money[respecter]%modulo)%modulo;
                    }
                }
            }
            money = moneyAfter2nDays.clone();
        }
 
        /* next t-1 days can be calculated by a formula
         money[i] = oneyAfterFirstDay[i] + ( Sum Of Respectors After First Day ) * (2^(t-2) - 1)
         where (2^(t-1) - 1) is multiplication constant
        */
        if(t > n*n) {
            t -= n*n;
            multiplicationConstant = modpow(2, t) - 1;
            for(int i = 1; i <= n; i++) {
                long sum = 0;
                boolean respectorHasRespectors = false;
                if(respectedBy.get(i) != null) {
                    for(int respecter : respectedBy.get(i)) {
                        sum = (sum%modulo + moneyAfter2nDays[respecter]%modulo)%modulo;
                        if(respectedBy.get(respecter) != null) respectorHasRespectors = true;
                    }
                    if(respectorHasRespectors) {
                        money[i] +=  ((sum%modulo) * (multiplicationConstant%modulo))%modulo;
                    } else {
                        money[i] +=  ((sum%modulo) * (t%modulo))%modulo;
                    }
                }
            }
        }
        for(int j = 1; j <= n; j++) {
            System.out.print(money[j] + " ");
        }
    }

    static long modpow(long x, long n) {
        if (n == 0) return 1;
        long u = modpow(x,n/2);
        u = (u*u)%modulo;
        if (n%2 == 1){
            u = (u*x)%modulo;
        }
        return u;
    }

}