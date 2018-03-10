// Barath Tirumala
// Assignment 1 Recitation
// ACM

import java.util.*;

public class acm {
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        int sum = 0;
        int checkright = 0;
        boolean flag = true;
        while(flag){
            int time = stdin. nextInt();
            if(time == -1){
                break;
            }
            String problem = stdin.next();
            String result = stdin.next();
            String right = "right";
            if(map.containsKey(problem)){
                int newkey = map.get(problem) + 1;
                map.put(problem, newkey);
            }
            else{
                map.put(problem, 1);
            }

            if(result.equals(right)){
                //System.out.println("ANSWER WAS RIGHT");
                int check = map.get(problem);
                sum+= time;
                sum+= (check-1)*20;
                checkright++;
            }
            //System.out.println(time+" "+problem+" "+result);

        }
        System.out.println(checkright+" " +sum);
        //System.out.println(sum);
    }
}
