// Barath Tirumala

import java.util.*;

public class bigtruck {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int numLoc = stdin.nextInt();

        int[] location = new int[numLoc];
        int[][] m = new int[numLoc][numLoc];
        int[][] path = new int[numLoc][numLoc];


        for(int i = 0; i <numLoc; i++){
            location[i] = stdin.nextInt();
        }
        int numRoads = stdin.nextInt();

        for (int a = 0; a < numLoc; a++)
            for (int b = 0; b < numLoc; b++)
                m[a][b] = 1000000;
        for(int k = 0; k<numRoads; k++){
            int t1 = stdin.nextInt();
            int t2 = stdin.nextInt();
            t1--; t2--;
            m[t1][t2] = stdin.nextInt();
        }


        for (int a = 0; a < numLoc; a++)
            for (int b = 0; b < numLoc; b++)
                if (m[a][b] == 1000000)
                    path[a][b] = -1;
                else
                    path[a][b] = a;

        for (int i=0; i<numLoc; i++)
            path[i][i] = i;

        int[][] shortpath = shortestpath(m, path, location);

        for (int i=0; i<numLoc;i++) {
            for (int j=0; j<numLoc;j++)
                System.out.print(shortpath[i][j]+" ");
            System.out.println();
        }

        numLoc--;
        String myPath = numLoc + "";

        // Just add start to our string and print.
        myPath = numLoc + " -> " + myPath;
        //System.out.println("Here's the path "+myPath);
    }

    public static int[][] shortestpath(int[][] adj, int[][] path, int[] loc) {

        int n = adj.length;
        int[][] ans = new int[n][n];
        int sum = 0;
        sum += loc[0];

        // Implement algorithm on a copy matrix so that the adjacency isn't destroyed.
        copy(ans, adj);

        // Compute successively better paths through vertex k.
        for (int k=0; k<n;k++) {

            // Do so between each possible pair of points.
            for (int i=0; i<n; i++) {
                for (int j=0; j<n;j++) {

                    // It's better to go through k as a intermediate vertex.
                    if (ans[i][k]+ans[k][j] < ans[i][j]) {
                        ans[i][j] = ans[i][k]+ans[k][j];
                        sum+=loc[j--];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }

        System.out.println(sum);
        // Return the shortest path matrix.
        return ans;
    }

    public static void copy(int[][] a, int[][] b) {
        for (int i=0;i<a.length;i++)
            for (int j=0;j<a[0].length;j++)
                a[i][j] = b[i][j];
    }

}

