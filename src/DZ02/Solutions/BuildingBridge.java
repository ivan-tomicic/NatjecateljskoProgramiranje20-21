package DZ02.Solutions;

import java.util.*;

public class BuildingBridge {

    static int N;
    static int M;
    static int A;
    static int B;

    static double[] ByCoordinates;
    static double[] AyCoordinates;
    static double[] BPathways;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        A = sc.nextInt();
        B = sc.nextInt();

        AyCoordinates = new double[N];
        ByCoordinates = new double[M];
        BPathways = new double[M];

        for(int i = 0; i < N; i++) {
            AyCoordinates[i] = sc.nextDouble();
        }

        for(int j = 0; j < M; j++) {
            ByCoordinates[j] = sc.nextDouble();
        }
        for(int j = 0; j < M; j++) {
            BPathways[j] = sc.nextDouble();
        }
        double max = 1E10;

        int x = 0, y = 0;
        int i = 0;
        for(int j = 0; j < M ; j++) {
            while(i + 1 < N && (distanceVillage1ToB(AyCoordinates[i + 1], ByCoordinates[j]) < distanceVillage1ToB(AyCoordinates[i], ByCoordinates[j]))) {
                i++;
            }
            if(max > distanceVillage1ToB(AyCoordinates[i], ByCoordinates[j]) + BPathways[j]) {
                max = distanceVillage1ToB(AyCoordinates[i], ByCoordinates[j]) + BPathways[j];
                x = i + 1;
                y = j + 1;
            }
        }
        System.out.print(x + " " + y);

    }

    public static double distanceVillage1ToB(double yA, double yB) {
        return (Math.sqrt(Math.pow(A, 2) + Math.pow(yA, 2)) + Math.sqrt(Math.pow(A - B, 2) + Math.pow(yA - yB, 2)));
    }
}
