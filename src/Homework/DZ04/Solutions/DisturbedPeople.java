package Homework.DZ04.Solutions;

import java.util.Scanner;

public class DisturbedPeople {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] input = sc.nextLine().split("\\s+");

        int counter = 0;
        for(int i = 0; i < input.length - 2; i++){

            if(input[i].equals("1") && input[i + 1].equals("0") && input[i + 2].equals("1")) {
                counter++;
                i += 2;
            }
        }
        System.out.println(counter);
    }
}
