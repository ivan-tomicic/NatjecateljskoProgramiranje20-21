package Homework.DZ02.Solutions;

import java.util.Scanner;

public class MoreCowbell {

    static int N; //number of cowbells
    static int K; //number of boxes
    static int[] cowbells;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        cowbells = new int[N];
        for(int i = 0; i < N; i++) {
            cowbells[i] = sc.nextInt();
        }
        //We are searching for the greatest s (box size) for which the cowbells cannot fit into
        //boxes, and the answer is that s + 1

        int upperBound = cowbells[cowbells.length - 1] * 2;
        int minimalBoxSize = -1;
        for(int jump = upperBound; jump >= 1; jump /= 2) {
            while(!valid(minimalBoxSize + jump)) {
                minimalBoxSize += jump;
            }
        }
        minimalBoxSize++;
        System.out.print(minimalBoxSize);

    }

    static boolean valid(int boxSize) {
        if (cowbells[cowbells.length - 1] > boxSize) {
            return false;
        }
        if(K >= N) {
            return true;
        }

        int rightIndex = cowbells.length - 1;

        for(; rightIndex > cowbells.length - 1 - (K*2 - N); rightIndex--) {} //skip K*2 - N largest cowbells

        for(int leftIndex = 0; leftIndex < rightIndex; leftIndex++) {
            if(cowbells[leftIndex] + cowbells[rightIndex] > boxSize) {
                return false;
            }
            rightIndex--;
        }
        return true;
    }
}
