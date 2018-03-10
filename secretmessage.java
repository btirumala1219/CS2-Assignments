// Barath Tirumala
// Assignment 1 Recitation
// Secret Message

import java.util.*;


public class secretmessage {
    private String myString;
    private secretmessage(String string){
       // myString = new String(string);
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
        for(int i = 0; i<numCases; i++){
            String word = new String();
            word = stdin.next();
            if(word.length() >100){
                System.out.println("ERROR");
                return;
            }
            int length = word.length();
            int k = (int)Math.sqrt(length);
            int ksqred = k*k;
            if(ksqred != length){
                k++;
            }
            ksqred = k*k;
            //System.out.println(k);

            char Grid[][] = new char[k][k];
            char Rotated[][] = new char[k][k];
            for(int j = 0; j<(ksqred-length); j++ ){
                word += '*';
            }
            //System.out.println(word);
            int temp = 0;
            for(int jj= 0; jj<k; jj++){
                for(int kk = 0; kk<k; kk++){
                    Grid[jj][kk]= word.charAt(temp);
                    temp++;
                }
            }
            //print(Grid, k);
            //System.out.println();
            //Rotate(Grid,k);
            printRotate(Grid,k);

            System.out.println();



        }



    }

    public static void print(char[][] array, int k){
        for(int i = 0; i<k; i++){
            for(int j = 0; j<k; j++){
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }
    public static void Rotate(char[][] array, int k){
        for(int i = 0; i<k; i++){
            for(int j = k-1; j>=0; j--){
                System.out.print(array[j][i]);
            }
            System.out.println();
        }
    }
    public static void printRotate(char[][] array, int k){
        for(int i = 0; i<k; i++){
            for(int j = k-1; j>=0; j--){
                if(array[j][i]=='*'){
                    continue;
                }
                System.out.print(array[j][i]);
            }
        }
    }


    /*
    for(int a = 0; a<size; a++){
            if(array[3-a%bval][a/bval] == '*'){
                continue;
            }
            System.out.print(array[3-a%bval][a/bval]);
        }
     */


}
