//Barath Tirumala
//CS2 Arup

import java.util.*;

class pacman
{
    public static void main(String args[])
    {
        pacman pac = new pacman();
        int cost[][]= new int[][]{{0, 2, 4, 3},
                {2,9,6,9},
                {13,3,8,0}};
        System.out.println(pac.maxCost(cost,3,4));
    }

    public int  maxCost(int[][] cost, int m, int n){


        int[][] temp = new int[m][n];
        temp[0][0] = cost[0][0];
        for(int i = 1;  i<n; i++){
            temp[0][i] = cost[0][i] + temp[0][i-1];
        }
        for(int ii = 1; ii<m; ii++){
            temp[ii][0] = cost[ii][0] + temp[ii-1][0];
        }

        for(int x=1; x<m; x++){
            for(int y = 1; y<n; y++){
                temp[x][y] = cost[x][y] + Math.max(temp[x][y-1], temp[x-1][y]);
            }
        }
        return Math.max(temp[m-1][n-2], temp[m-2][n-1]);
    }

    public void printArray(int arr[][], int m, int n){
        for(int x=0; x<m; x++){
            for(int y = 0; y<n; y++){
                System.out.print(arr[x][y] + "  ");
            }
            System.out.println();
        }
    }
}