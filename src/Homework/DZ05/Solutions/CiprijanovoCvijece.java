package Homework.DZ05.Solutions;


import java.util.Scanner;

public class CiprijanovoCvijece {

    static int n,m;
    static int sum[][];
    static int lastMoves[][];
    static int matrix[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        sum = new int[n+1][m+1];
        matrix = new int[n+1][m+1];
        lastMoves = new int[n+1][m+1];


        for(int i = 1; i <= n; i++) {
            String s = sc.next();
            for(int j = 1; j <= m; j++) {
                if(s.charAt(j - 1) == 'X') matrix[i][j] = 1;
                else matrix[i][j] = 0;
                if(i/1.0 < j/2.0 || i/1.0 > 2*j/1.0) matrix[i][j] = 0;
            }
        }
        /*for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                System.out.print(lastMoves[i][j] + " ");
            }
            System.out.println();
        }*/


        //1 ->  desno,  2 ->  dolje,  3 -> doljedesno

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(i/1.0 < j/2.0 || i/1.0 > 2*j/1.0) continue;
                if(i == 1 && j == 1) {
                    sum[i][j] = matrix[i][j];
                    continue;
                }
                if(i == 1 && j == 2) {
                    sum[i][j] = sum[1][1] + matrix[i][j];
                    lastMoves[i][j] = 1;
                }
                else if(j == 1 && i == 2) {
                    sum[i][j] = sum[1][1] + matrix[i][j];
                    lastMoves[i][j] = 2;
                }
                else if((sum[i-1][j-1] >= sum[i][j-1] || lastMoves[i][j-1]==1) && (sum[i-1][j-1] >= sum[i-1][j]  || lastMoves[i-1][j]==2) && lastMoves[i-1][j-1] != 3){
                    sum[i][j] = sum[i-1][j-1] + matrix[i][j];
                    lastMoves[i][j] = 3;
                }
                else if((sum[i][j-1] >= sum[i-1][j] || lastMoves[i-1][j]==2) && (sum[i][j-1] >= sum[i-1][j-1] || lastMoves[i-1][j-1]==3) && lastMoves[i][j-1] != 1) {
                    sum[i][j] = sum[i][j-1] + matrix[i][j];
                    lastMoves[i][j] = 1;

                }else if((sum[i-1][j] >= sum[i][j-1] || lastMoves[i][j-1]==1) && (sum[i-1][j] >= sum[i-1][j-1]  || lastMoves[i-1][j-1]==3) && lastMoves[i-1][j] != 2) {
                    sum[i][j] = sum[i-1][j] + matrix[i][j];
                    lastMoves[i][j] = 2;
                }

                //System.out.print("(" + i + ", " + j + "): " + lastMoves[i][j] + " ");
            }
            //System.out.println();
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                max = Math.max(max, sum[i][j]);
            }
        }
        System.out.print(max);

    }
}
