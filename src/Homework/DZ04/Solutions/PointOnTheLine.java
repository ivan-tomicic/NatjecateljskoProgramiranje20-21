package Homework.DZ04.Solutions;

import java.util.*;

public class PointOnTheLine {

    public static void main(String[] args) {
        int n,d;
        List<Integer> points = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        d = sc.nextInt();
        for(int i = 0; i < n; i++) {
            points.add(sc.nextInt());
        }
        points.sort(Comparator.naturalOrder());
        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(Math.abs(points.get(j) - points.get(i)) <= d) {
                    max = Math.max(max, Math.abs(j - i));
                }
            }
        }
        System.out.println(n - max - 1);
    }
}
