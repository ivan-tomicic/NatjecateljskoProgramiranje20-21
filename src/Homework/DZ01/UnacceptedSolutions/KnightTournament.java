package Homework.DZ01.UnacceptedSolutions;

import java.util.ArrayList;
import java.util.Scanner;

//this solution doesn't work on example 22.
public class KnightTournament {

    static int N;
    static int M;
    static int[] segmentTree;
    static ArrayList<Integer> battles;
    static int nextPowerOfTwo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        nextPowerOfTwo = (int) Math.pow(2, Math.ceil(Math.log10(N)/Math.log10(2)));
        segmentTree = new int[2*nextPowerOfTwo];

        battles = new ArrayList<>(M*3);

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < 3; j++) {
                battles.add(sc.nextInt());
            }
        }

        for(int i = M-1; i >= 0; i--) {
            int l = battles.get(i*3);
            int r = battles.get(i*3 + 1);
            int x = battles.get(i*3 + 2);

            if(l <= x - 1) {
                updateTree(l, x - 1, x);
            }
            if(r >= x + 1) {
                updateTree(x + 1, r, x);
            }
            //printTree();
        }
        for(int i = 1; i <= N; i++) {
            System.out.print(getWinnerOf(i) + " ");
        }
    }


    public static void updateTree(int l, int r, int v) {
        int a = l + nextPowerOfTwo - 1;
        int b = r + nextPowerOfTwo - 1;

        while (a <= b) {
            segmentTree[a] = 0;
            segmentTree[b] = 0;
            if (a%2 == 1) {
                segmentTree[a++] = v;
            }

            if (b%2 == 0) {
                segmentTree[b--] = v;
            }
            a /= 2;
            b /= 2;
        }
    }

    public static int getWinnerOf(int x) {
        x += nextPowerOfTwo - 1;
        while(x >= 2) {
            if(segmentTree[x] == 0) {
                x /= 2;
            } else {
                return segmentTree[x];
            }
        }
        return segmentTree[x];
    }



    public static void printTree() {
        System.out.println("--------------------");
        for(int i = 0; i < segmentTree.length; i++) {
            System.out.println(segmentTree[i] + " ");
        }
        System.out.println("--------------------");
    }


}