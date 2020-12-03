package Homework.DZ01.Solutions;

import java.util.Scanner;

public class GregAndArray {

    static long[] segmentTree;
    static int nextPowerOfTwoN;

    static long[] operationOccurrencesSegmentTree;
    static int nextPowerOfTwoM;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        nextPowerOfTwoN = (int) Math.pow(2, Math.ceil(Math.log10(N + 1)/Math.log10(2)));
        segmentTree = new long[2*nextPowerOfTwoN];

        nextPowerOfTwoM = (int) Math.pow(2, Math.ceil(Math.log10(M + 1)/Math.log10(2)));
        operationOccurrencesSegmentTree = new long[nextPowerOfTwoM*2];

        long firstElement = sc.nextLong();
        segmentTree[nextPowerOfTwoN] = firstElement;

        for(int i = nextPowerOfTwoN + 1; i < N + nextPowerOfTwoN; i++) {
            long secondElement = sc.nextLong();
            segmentTree[i] = secondElement - firstElement;
            firstElement = secondElement;
        }
        segmentTree[1] = updateChildrenOfIndex(1);

        int[][] operations = new int[M][3];

        for(int i = 0; i < M; i++) {
            operations[i][0] = sc.nextInt();
            operations[i][1] = sc.nextInt();
            operations[i][2] = sc.nextInt();
        }

        for(int j = 0; j < K; j++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            updateTree(x, y, 1, operationOccurrencesSegmentTree, nextPowerOfTwoM);
        }

        for(int i = 0; i < M; i++) {
            int l = operations[i][0];
            int r = operations[i][1];
            long v = operations[i][2] * getSum(1, i + 1, operationOccurrencesSegmentTree, nextPowerOfTwoM);
            updateTree(l , r,  v, segmentTree, nextPowerOfTwoN);
        }

        for(int i = 1; i <= N; i++) {
            System.out.print(getSum(1, i, segmentTree, nextPowerOfTwoN) + " ");
        }
    }

    public static long updateChildrenOfIndex(int index) {
        if(index*2 >= nextPowerOfTwoN) {
            return segmentTree[index] = segmentTree[index*2] + segmentTree[index*2 + 1];
        }
        return segmentTree[index] = updateChildrenOfIndex(index*2) + updateChildrenOfIndex(index*2 + 1);
    }

    public static void updateTree(int l, int r, long v, long[] tree, int nextPower) {
        int a = l + nextPower - 1;
        int b = r + nextPower;
        tree[a] += v;
        tree[b] -= v;

        a /= 2;
        b /= 2;
        while (a > 0) {
            tree[a] += v;
            a /=2;
        }
        while (b > 0) {
            tree[b] -= v;
            b /= 2;
        }
    }

    public static long getSum(int l, int r, long[] tree, int nextPower) {
        l += nextPower - 1;
        r += nextPower - 1;
        long sum = 0;

        while(l <= r) {
            if(l%2 == 1) {
                sum += tree[l++];
            }
            if(r%2 == 0) {
                sum += tree[r--];
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }

}
