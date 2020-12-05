package Homework.DZ05.Solutions;

import java.util.*;

public class AndrijinAutomat {

    static Map<Integer, Character> coins = new LinkedHashMap<>();
    static int n,m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 0; i < n; i++) {
            coins.put(sc.nextInt(), sc.next().charAt(0));
        }

        System.out.println(solve(m));
    }

    static int solve(int x) {
        if (x < 0) return 1002;
        if (x == 0) return 0;
        int best = 1001;
        for (int c : coins.keySet()) {
            if(coins.get(c) != coins.get(x-c)) {
                best = Math.min(best, solve(x-c) + 1);
            }
        }
        return best;
    }



}
