package DZ01.Solutions;

import java.util.Scanner;

/*
 Prvo se radi lista razlika (difference array) elemenata, a onda nad njom segmentno stablo
 (moglo se i Binary Indexed Tree). Radimo difference array jer je u zadatku moramo mijenjati vrijednosti
 na intervalu za v, a ona je pogodna struktura za taj tip problema
 */
public class Suncica {

    static int N;
    static int Q;
    static long[] segmentTree;
    static int nextPowerOfTwo;  // ulazni niz se poveca na sljedecu potenciju broja 2 kako bi se
                                //izgradilo segmentno stablo

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        nextPowerOfTwo = (int) Math.pow(2, Math.ceil(Math.log10(N + 1)/Math.log10(2)));
        segmentTree = new long[2*nextPowerOfTwo];

        long firstElement = sc.nextLong();
        segmentTree[nextPowerOfTwo] = firstElement;
        for(int i = nextPowerOfTwo + 1; i < N + nextPowerOfTwo; i++) {
            long secondElement = sc.nextLong();
            segmentTree[i] = secondElement - firstElement;
            firstElement = secondElement;
        }
        segmentTree[1] = updateChildrenOfIndex(1);

        for(int i = 0; i < Q; i++) {
            int upit = sc.nextInt();
            if(upit == 1) {
                int l = sc.nextInt();
                int r = sc.nextInt();
                int v = sc.nextInt();
                updateTree(l, r, v);

            } else {
                int x = sc.nextInt();   //racunamo sumu na intervalu 1, x da bi smo dobili vrijednost na poziciji x
                long sum = getSum(1,x);
                System.out.println(sum);
            }
        }

    }

    public static long updateChildrenOfIndex(int index) {
        if(index*2 >= nextPowerOfTwo) {
            return segmentTree[index] = segmentTree[index*2] + segmentTree[index*2 + 1];
        }
        return segmentTree[index] = updateChildrenOfIndex(index*2) + updateChildrenOfIndex(index*2 + 1);
    }


    public static void updateTree(int l, int r, int v) {
        int a = l + nextPowerOfTwo - 1;
        int b = r + nextPowerOfTwo;
        segmentTree[a] += v;      //povecat cemo za v vrijednost u difference array na l-tom elementu
        segmentTree[b] -= v;      //smanjit cemo za v vrijednost u difference array na r+1-tom elementu

        a /= 2;
        b /= 2;
        while (a > 0) {           //sve pretke od l povecaj za v
            segmentTree[a] += v;
            a /=2;
        }
        while (b > 0) {     //sve pretke od r+1 smanji za v
            segmentTree[b] -= v;
            b /= 2;
        }
    }

    public static long getSum(int l, int r) {
        l += nextPowerOfTwo - 1;
        r += nextPowerOfTwo - 1;
        long sum = 0;

        while(l <= r) {
            if(l%2 == 1) {
                sum += segmentTree[l++];
            }
            if(r%2 == 0) {
                sum += segmentTree[r--];
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }

    public static void printTree() {
        System.out.println("--------------------");
        for(int i = 0; i < segmentTree.length; i++) {
            System.out.println(segmentTree[i] + " ");
        }
        System.out.println("--------------------");
    }
}
