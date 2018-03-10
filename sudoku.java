// Barath Tirumala
// Assignment 1 CS2
// Sudoku

import java.util.*;

public class sudoku {

    //declare board
    private int[] board;

    //declare sudoku onbject class
    private sudoku(int boxy[]){
        board = new int[81];
        for (int i=0; i<81; i++)
            board[i] = boxy[i];
    }

    //function to solve the puzzle
    public boolean sandp(int [] sq) {
        //check for each square
        for( int i = 0; i<81; i++){
            if(sq[i]==0){//if unassigned then continue
                for(int num = 1; num<= 9; num++){
                    if(!checkBox(sq, i, num) && !checkCols(sq, i, num) && !checkRows(sq, i, num)){//check to see if this arrangement is valid
                        sq[i] = num;//if so then insert it
                        if(sandp(sq)){
                            return true;//recursively call function for this case
                        }
                        else{
                            sq[i] = 0;//otherwise leave as it was
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }


    public boolean checkRows(int arr[], int cnt, int num){
        //calculate what row the box is on
        int row = cnt/9;
        int tst = num;
        int temp1 = row*9;
        int line = temp1;
        int temp2 = line +9;
        int var;
        for(var = line; var <temp2; var++){
            if (arr[var] == tst){//check if this shows up in the row
                //System.out.print("ROW TEST HAS FAILED");
                return true;
            }
        }
        return false;
    }

    public boolean checkCols(int arr[], int cnt, int num){
        //calculate what column this box is on
        int col = cnt%9;
        int tst = num;
        int line;
        int max;
        for(line = 0; line<9; line++){
            max = line*9 + col;
            if (arr[max] == tst){//check if this number shows up in the column
                //System.out.print("FAIL COL" + cnt);
                return true;
            }
        }
        return false;
    }

    public boolean checkBox(int arr[], int cnt, int num){
        int tst = num;
        int i;
        int row = cnt/9;
        int col = cnt%9;//get the row and column of this box
        for(i = 0; i<81; i++){
            if((i/9)/3 == row/3){
                if((i%9)/3 == col/3){
                    if (arr[i] == tst){//check to see if it shows up
                        //System.out.print("FAIL BOX" + cnt);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //function to print out the whole puzzle
    public void prinb(int box[]){
        int ii = 0 , jj;
        while(ii < 81){
            for (jj = 0; jj <9; jj++){
                System.out.print(box[ii] + " ");
                ii++;
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    //check if every slot in the sudoku puzzle is filled or not
    public boolean checkzero(int box[]){
        for(int i = 0; i<81; i++){
            if(box[i]==0){
                return false;
            }
        }
        return true;
    }

    //check if the puzzle is solved
    public boolean checksolve(int box[]){
        int arr[] = new int[9];
        for(int k = 0; k<9; k++){
            arr[k] = 0;
        }
        for(int i = 0; i<81; i++){
            arr[box[i]-1]++;
        }
        for(int k = 0; k<9; k++){
            if(arr[k] != 9){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //scan number of cases
        Scanner stdin = new Scanner(System.in);
        int numBoards = stdin.nextInt();

        //System.out.println(numBoards);

        int sqre[] = new int[81];
        int qre[] = new int[81];
        sudoku myBoard = new sudoku(qre);

        // Process all cases.
        for (int loop=1; loop<=numBoards; loop++) {

            for (int i=0; i<81; i++)
                sqre[i] = stdin.nextInt();

            //solve the case
            myBoard.board = sqre;
            myBoard.sandp(myBoard.board);
            System.out.println("Test Case "+loop+":");
            System.out.println();

            //check to see if it is an invalid case
            /*
            if(myBoard.checkzero(myBoard.board)){
                if(myBoard.checksolve((myBoard.board))){
                    System.out.println("No solution possible.");
                    System.out.println();
                    System.out.println();
                    continue;
                }
            }
            */
            //if valid print solution
            if(myBoard.sandp(sqre)){
                myBoard.prinb(myBoard.board);
            }
            else{
                System.out.println("No solution possible.");
                System.out.println();
                System.out.println();
            }
        }
    }
}
