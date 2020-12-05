package Homework.DZ04.Solutions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class NonZeroSegments {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        int n = sc.nextInt();
        long sum = 0;
        Map<Long, Boolean> map = new LinkedHashMap<>();
        map.put(0L, true);

        for(int i = 0; i < n; i++) {
            long number = sc.nextLong();
            sum += number;
            System.out.println("\n" + map + " counter: " + counter);
            if(map.keySet().contains(sum) && map.get(sum)) {
                counter++;
                sum = number;
                map.clear();
                map.put(0L, true);
            }
            map.put(sum, true);
        }

        System.out.println(counter);
    }
}
