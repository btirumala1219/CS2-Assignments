//Barath Tirumala
//CS2 Arup Guha

import java.util.*;

class pacman
{
    public static void main(String args[])
    {
        pacman pac = new pacman();
        Scanner stdin = new Scanner(System.in);
        // Scan in the number of rows and columns
        int m = stdin.nextInt();
        int n = stdin.nextInt();

        int sum = 0;
        String check = "";

        //check to see if the board has only one row or column. If so, then return the straight sum of the values
        if(m ==1){
            for(int i = 0; i<n-1; i++){
                if(i==0 || i==n){
                    String Temp = stdin.next();
                    continue;
                }
                sum += stdin.nextInt();
                check = 'R' + check;

            }
            System.out.println(sum + " 1");
            System.out.println(check);
            return;
        }

        if(n==1){
            for(int i = 0; i<m-1; i++){
                if(i==0 || i==m){
                    String Temp = stdin.next();
                    continue;
                }

                sum += stdin.nextInt();
                check = 'D' + check;
            }
            System.out.println(sum + " 1");
            System.out.println(check);
            return;
        }

        // Scan in rest of the board if the dimensions are larger than 2
        int cost[][]= new int[m][n];
        for(int x=0; x<m; x++) {
            for (int y = 0; y < n; y++) {
                if(x == 0 && y == 0){
                    cost[x][y] = -1;
                    String Temp = stdin.next();
                    continue;
                }
                if(x== m-1 && y==n-1){
                    cost[x][y] = -2;
                    String Temp = stdin.next();
                    continue;
                }
                cost[x][y] = stdin.nextInt();
            }
        }

        // call maxcost algorithm
        pac.maxCost(cost,m,n);
    }

    public void maxCost(int[][] cost, int m, int n){

        int[][] temp = new int[m][n]; //array to keep running totals
        int[][] count = new int[m][n]; //array to keep running count of ways to reach
        temp[0][0] = cost[0][0]; //set the starting pt same
        count[0][0] = 1;

        //add up totals for sides
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
                //keep running maximum. If tied, then choose one with higher max sum. This algorithm will allow us to find the max we can hold

                if(temp[x][y-1] > temp[x-1][y]){
                    temp[x][y] = cost[x][y] + temp[x][y-1];
                }
                else if(temp[x][y-1] < temp[x-1][y]){
                    temp[x][y] = cost[x][y] + temp[x-1][y];
                }
                else{
                    temp[x][y] = cost[x][y] + temp[x-1][y];
                }

                //this array keeps max number of paths. After each one mod by the value

                if(cost[x-1][y] == cost[x][y-1]){
                    count[x][y] = (count[x-1][y] + count[x][y-1])%(10^9 +7);
                }
                else if((temp[x][y] - cost[x][y]) == temp[x-1][y]){
                    count[x][y] = (count[x-1][y])%(10^9 +7);
                }
                else if((temp[x][y] - cost[x][y]) == temp[x][y-1]){
                    count[x][y] = (count[x][y-1])%(10^9 +7);
                }
            }
        }

        // get total number of paths
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

        //max cost held
        int ans = Math.max(temp[m-1][n-2],temp[m-2][n-1]);

        int tempo = 0;

        String out = "";
        int x;
        int y;

        //generate the actual path itself, giving priority to go down first and then right
        if(ans == temp[m-1][n-2]){
            x=m-1;
            y=n-2;
            out = "R";
        }
        else{
            x=m-2;
            y=n-1;
            out = "D";
        }

        //System.out.println(temp[m-1][n-2] + " " + temp[m-2][n-1]);


        //Giving priority to go down first will result in the alphabetical answer
        for(int i = 0; i< m+n; i++){
            if(tempo == -1){
                continue;
            }
            if(y==0){
                x = x-1;
                out = 'D' + out;
                tempo=cost[x][y];
                continue;
            }
            if((temp[x][y] - cost[x][y]) == temp[x][y-1]){
                out = 'R' + out;
                y=y-1;
                tempo=cost[x][y];
                continue;
            }
            else{
                out = 'D' + out;
                x = x-1;
                tempo=cost[x][y];
                continue;
            }

        }

        ans++; //counteract setting start point as -1

        //print out answer
        System.out.println(ans + " " + cntans);
        System.out.println(out);
    }


    //print array function just for testing purposes

    public void printArray(int arr[][], int m, int n){
        for(int x=0; x<m; x++){
            for(int y = 0; y<n; y++){
                System.out.print(arr[x][y] + "  ");
            }
            System.out.println();
        }
    }

}