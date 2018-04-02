//Barath Tirumala


import java.util.*;

public class countseq {

    final public static int UNFILLED = -1;

    public static void main(String[] args) {
        //Scan in number of cases
        Scanner stdin = new Scanner(System.in);
        int numcases = stdin.nextInt();

        for(int i = 0; i<numcases; i++){ //loop through cases
            String s1 = stdin.next(); //scan in string 1 and 2
            String s2 = stdin.next();
            long[][] memo = new long[s1.length()+1][s2.length()+1]; //initialize array to store memoization
            for (int k=0; k<memo.length; k++) Arrays.fill(memo[k], UNFILLED); //set all to -1 as unused
            System.out.println(count(s1, s2, s1.length(), s2.length(), memo));
        }

        //System.out.println(count(a, b, a.length(), b.length()));

    }

    static long count(String s1, String s2, int m, int n, long[][] memo){
        if((m == 0 && n == 0) || n==0) return 1; //check if second string is 0, or they are both 0 so there is 1 common string

        if(m==0) return 0; //if first string is 0 then there are no common strings

        if (memo[m][n] != UNFILLED) return memo[m][n]; //if this calculation has already been done, return it

        if(s1.charAt(m-1) == s2.charAt(n-1)){
            memo[m][n] = count(s1,s2,m-1,n-1, memo)+count(s1,s2,m-1,n, memo); //store calculation in memo array
        }

        else{
            memo[m][n]= count(s1,s2,m-1,n, memo);
        }
        //System.out.println(memo);
        return memo[m][n]; //return memo value

    }
}
