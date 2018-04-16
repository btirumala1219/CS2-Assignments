//Barath Tirumala
//CS2 Arup

import java.util.*;

class pacman
{
    public static void main(String args[])
    {
        pacman pac = new pacman();
        Scanner stdin = new Scanner(System.in);
        int m = stdin.nextInt();
        int n = stdin.nextInt();
        int cost[][]= new int[m][n];
        for(int x=0; x<m; x++) {
            for (int y = 0; y < n; y++) {
                if(x == 0 && y == 0){
                    cost[x][y] = 0;
                    String Temp = stdin.next();
                    continue;
                }
                if(x== m-1 && y==n-1){
                    cost[x][y] = 0;
                    String Temp = stdin.next();
                    continue;
                }
                cost[x][y] = stdin.nextInt();
            }
        }

        pac.maxCost(cost,m,n);
    }

    public void maxCost(int[][] cost, int m, int n){

        int[][] temp = new int[m][n];
        int[][] count = new int[m][n];
        temp[0][0] = cost[0][0];
        count[0][0] = 1;
        for(int i = 1;  i<n; i++){
            temp[0][i] = cost[0][i] + temp[0][i-1];
            count[0][i] = 1;
        }
        for(int ii = 1; ii<m; ii++){
            temp[ii][0] = cost[ii][0] + temp[ii-1][0];
            count[ii][0] = 1;
        }

        for(int x=1; x<m; x++){
            for(int y = 1; y<n; y++){
                if(temp[x][y-1] > temp[x-1][y]){
                    temp[x][y] = cost[x][y] + temp[x][y-1];
                }
                else if(temp[x][y-1] < temp[x-1][y]){
                    temp[x][y] = cost[x][y] + temp[x-1][y];
                }
                else{
                    temp[x][y] = cost[x][y] + temp[x-1][y];
                }

                if(cost[x-1][y] == cost[x][y-1]){
                    count[x][y] = count[x-1][y] + count[x][y-1];
                }
                else if((temp[x][y] - cost[x][y]) == temp[x-1][y]){
                    count[x][y] = count[x-1][y];
                }
                else if((temp[x][y] - cost[x][y]) == temp[x][y-1]){
                    count[x][y] = count[x][y-1];
                }
            }
        }

        int cntans;
        if(temp[m-1][n-2]<temp[m-2][n-1]){
            cntans = count[m-2][n-1];
        }
        else if(temp[m-1][n-2]>temp[m-2][n-1]){
            cntans = count[m-1][n-2];
        }
        else{
            cntans = count[m-1][n-2] + count[m-2][n-1];
        }

        int ans = Math.max(temp[m-1][n-2],temp[m-2][n-1]);
        System.out.println(ans + " " + cntans);
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