package Homework.DZ01.Solutions;

import java.util.Scanner;

public class VjecniStudent {

    static int N;
    static int Q;
    static int[] segmentTree;
    static int nextPowerOfTwo; // ulazni niz se poveca na sljedecu potenciju broja 2 kako bi se
                                //izgradilo segmentno stablo

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        nextPowerOfTwo = (int) Math.pow(2, Math.ceil(Math.log10(N)/Math.log10(2)));
        segmentTree = new int[2*nextPowerOfTwo];

        for(int i = nextPowerOfTwo; i < N + nextPowerOfTwo; i++) {
            segmentTree[i] = sc.nextInt();
        }
        segmentTree[1] = updateChildrenOfIndex(1);


        for(int i = 0; i < Q; i++) {
            int upit = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(upit == 1) {
                int index1 = calculateIndexOfMax(a, b);          //nadji index 1. maxa na intervalu
                int max = segmentTree[index1];
                updateTree(index1 - nextPowerOfTwo + 1, 0); //makni taj max iz stabla

                int index2 = calculateIndexOfMax(a, b);          //nadji index sljedeceg maximuma
                updateTree(index1 - nextPowerOfTwo + 1, max); //vrati 1. max u stablo

                System.out.println(segmentTree[index2]);

            } else {
                updateTree(a, b);
            }
        }
    }

    public static int updateChildrenOfIndex(int index) {
        if(index*2 >= nextPowerOfTwo) {
            return segmentTree[index] = Math.max(segmentTree[index*2], segmentTree[index*2 + 1]);
        }
        return segmentTree[index] = Math.max(updateChildrenOfIndex(index*2), updateChildrenOfIndex(index*2 + 1));
    }


    public static void updateTree(int x, int y) {
        int index = x + nextPowerOfTwo - 1;
        segmentTree[index] = y;
        index /= 2;
        while (index > 0) {
            segmentTree[index] = Math.max(segmentTree[index*2],segmentTree[index*2 + 1]);
            index/=2;
        }
    }

    public static int calculateIndexOfMax(int l, int r) {
        l += nextPowerOfTwo - 1;
        r += nextPowerOfTwo - 1;
        int max = 0;
        int index = - 1;

        while(l <= r) {
            if(l%2 == 1) {
                if(segmentTree[l] > max) {
                    max = segmentTree[l];
                    index = l;
                }
                l++;
            }
            if(r%2 == 0) {
                if(segmentTree[r] > max) {
                    max = segmentTree[r];
                    index = r;
                }
                r--;
            }
            l /= 2;
            r /= 2;
        }

        while(index < nextPowerOfTwo) {
            if(segmentTree[index*2] == max) {
                index = index*2;
            } else {
                index = index*2 + 1;
            }
        }
        return index;
    }

    /*
    Pomocna funkcija koja ispisuje segmentno stablo.
     */
    public static void printTree() {
        System.out.println("--------------------");
        for(int i = 0; i < segmentTree.length; i++) {
            System.out.println(segmentTree[i] + " ");
        }
        System.out.println("--------------------");
    }
}
