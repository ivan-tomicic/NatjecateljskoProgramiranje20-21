package DZ02.Solutions;

import java.util.*;

public class Years {

    static int N;
    static TreeMap<Integer, Integer> years;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        years = new TreeMap<>();

        for(int i = 0; i < N; i++) {
            int birth = sc.nextInt();
            int death = sc.nextInt();

            years.merge(birth, 1, Integer::sum);
            years.merge(death, -1, Integer::sum);
        }

        int maxAlivePeople = 0;
        int yearOfMax = 0;
        int cnt = 0;
        for(Integer year : years.keySet()) {
            cnt += years.get(year);
            if(maxAlivePeople < cnt) {
                maxAlivePeople = cnt;
                yearOfMax = year;
            }
        }
        System.out.print(yearOfMax + " " + maxAlivePeople);
    }
}
