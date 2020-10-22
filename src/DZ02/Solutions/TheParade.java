package DZ02.Solutions;

import java.util.Scanner;

public class TheParade {

    static long numberOfRows;
    static int N;
    static long[] numberOfHeightsArray;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCases = sc.nextInt();
        for(int i = 0; i < testCases; i++) {

            N = sc.nextInt(); //number of heights [1, 30 000]
            numberOfRows = sc.nextLong(); //number of rows  [1, 10^12]
            numberOfHeightsArray = new long[N + 1];

            long upperBound = 0;
            for(int j = 1; j < N + 1; j++) {
                long soldiers = sc.nextLong();
                numberOfHeightsArray[j] = soldiers;
                upperBound += soldiers;
            }

            //We are searching for the greatest number of soldiers that can participate in the parade
            //using binary search on valid function
            upperBound = upperBound/numberOfRows * numberOfRows;
            long maximumNumberOfSoldiers = 0;
            for(long jump = upperBound; jump >= 1; jump /= 2) {
                while(valid(maximumNumberOfSoldiers + jump)) {
                    maximumNumberOfSoldiers += jump;
                }
            }
            System.out.println(maximumNumberOfSoldiers);
        }
    }

    /*
    We try to fill the parade with numberOfSoldiers by going through the numberOfHeights array
    and filling rows with two adjacent heights.
     */
    static boolean valid(long numberOfSoldiers) { //is it possible to make a parade with that number of
                                                // soldiers and rows?
        if(numberOfSoldiers % numberOfRows != 0) {
            numberOfSoldiers = (numberOfSoldiers/numberOfRows + 1) * numberOfRows;
        }

        long columnSize = numberOfSoldiers/numberOfRows;
        long[] temporaryHeightsArray = numberOfHeightsArray.clone();
        long positionsLeft = numberOfSoldiers;

        for(int height = 1; height <= numberOfHeightsArray.length - 1; height++) {
            long sum;
            if(height != 1 && (sum = temporaryHeightsArray[height] + temporaryHeightsArray[height - 1]) >= columnSize) {
                long positionsItCanFill = sum/columnSize * columnSize;
                temporaryHeightsArray[height] -= positionsItCanFill - temporaryHeightsArray[height - 1];
                temporaryHeightsArray[height - 1] = 0;
                positionsLeft -= positionsItCanFill;

            } else if((sum = temporaryHeightsArray[height]) >= columnSize) {
                long positionsItCanFill = sum/columnSize * columnSize;
                temporaryHeightsArray[height] -= positionsItCanFill;
                positionsLeft -= positionsItCanFill;

            } else if(height != numberOfHeightsArray.length - 1 && (sum = temporaryHeightsArray[height] + temporaryHeightsArray[height + 1]) >= columnSize) {
                long positionsItCanFill = sum/columnSize * columnSize;
                temporaryHeightsArray[height + 1] -= positionsItCanFill - temporaryHeightsArray[height];
                temporaryHeightsArray[height] = 0;
                positionsLeft -= positionsItCanFill;

            }
            if(positionsLeft <= 0) {
                return true;
            }
        }
        return false;
    }

    public static void printHeightsArray(long[] heights) {
        for (long height : heights) {
            System.out.print(height + " ");
        }
        System.out.println();
    }
}
