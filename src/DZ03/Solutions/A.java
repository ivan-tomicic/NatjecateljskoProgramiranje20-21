package DZ03.Solutions;

import java.util.*;

public class A {
    final static int modulo = 1000000007;
    static int n;
    static int m;
    static long t;
    static long multiplicationConstant;
    static LinkedHashMap<Integer, ArrayList<Integer>> respects; //key respecter respects all the people in its value
    static LinkedHashMap<Integer, ArrayList<Integer>> respectedBy; //key respected is respected by all the people in ints value

    static int[] moneyAfterFirstDay;
    static int[] money;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        respects = new LinkedHashMap<>();
        respectedBy = new LinkedHashMap<>();

        moneyAfterFirstDay = new int[n + 1];
        money = new int[n + 1];

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
            moneyAfterFirstDay[i] = sc.nextInt();
        }
        money = moneyAfterFirstDay.clone();

        t = sc.nextLong();
        multiplicationConstant = modpow(2, t - 1) - 1;

        //on the first day we increase each person's money by the money of its respectors
        for(int j = 1; j <= n; j++) {
            if (respectedBy.get(j) != null) {
                for(int respecter : respectedBy.get(j)) {
                    moneyAfterFirstDay[j] += money[respecter];
                }
            }
        }
        money = moneyAfterFirstDay.clone();
        /* next t-1 days can be calculated by a formula
         money[i] = oneyAfterFirstDay[i] + ( Sum Of Respectors After First Day ) * (2^(t-2) - 1)
         where (2^(t-1) - 1) is multiplication constant
        */
        for(int i = 1; i <= n; i++) {
            long sum = 0;
            boolean respectorHasRespectors = false;
            if(respectedBy.get(i) != null) {
                for(int respecter : respectedBy.get(i)) {
                    sum += moneyAfterFirstDay[respecter]%modulo;
                    sum %= modulo;

                    if(respectedBy.get(respecter) != null) respectorHasRespectors = true;
                }
                if(respectorHasRespectors) {
                    money[i] += (int) ((sum%modulo * multiplicationConstant)%modulo);
                } else {
                    money[i] += (int) (sum%modulo * (t - 1))%modulo;
                }

            }
        }

        for(int j = 1; j <= n; j++) {
            System.out.print(money[j] + " ");
        }

    }

    static long modpow(int x, long n) {
        if (n == 0) return 1;
        long u = modpow(x,n/2);
        u = (u*u)%modulo;
        if (n%2 == 1){
            u = (u*x)%modulo;
        }
        return u;
    }

}
