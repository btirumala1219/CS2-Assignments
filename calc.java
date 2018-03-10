//Barath Tirumala
//Programming Assignment Calc

import java.util.*;

public class calc {
    public static void main(String[] args) {

        //scan in number of test cases
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
        //int k = 1234567;
        //k = k%1000000;
        //System.out.println(k);

        for(int i = 0; i<numCases; i++){ //process through each case
            int a = stdin.nextInt(); //scan in a, b, and c
            int b = stdin.nextInt();
            int c = stdin.nextInt();
            int target = stdin.nextInt(); //target number
            boolean[] used = new boolean[1000000]; //declare boolean used array

            int sol = BFS(target, used, a, b, c); //solve
            System.out.println(sol); //print solution

        }
    }

    public static int BFS(int targ, boolean[] used, int a, int b, int c){
        Queue<Integer> q=new LinkedList<Integer>(); //set up queue
        int[] distance = new int[1000000]; //set up distance array
        q.add(0); //add initial element
        distance[0] = 0; //set distance to begin with
        used[0] = true; //mark as true
        int sol = -1; //set default solution value

        while(q.size()>0){ //while list isnt empty, run
            int cur = q.poll(); //get current value
            int nexta = cur + a; //adjust by each constant
            nexta = nexta%1000000;
            int nextb = cur*b;
            nextb = nextb%1000000;
            int nextc = cur/c;
            nextc = nextc%1000000;
            if(!used[nexta]){ //check if this is used already
                q.offer(nexta); //put in queue
                used[nexta] = true; //set as used
                distance[nexta] = distance[cur] + 1; //update distance array
                if(nexta == targ){ //check if we reached solution
                    sol = distance[nexta]; //if we did, this is the answer
                    break;
                }
            }
//repeat for all other cases of b and c
            if(!used[nextb]){
                q.offer(nextb);
                used[nextb] = true;
                distance[nextb] = distance[cur] + 1;
                if(nextb == targ){
                    sol = distance[nexta];
                    break;
                }
            }

            if(!used[nextc]){
                q.offer(nextc);
                used[nextc] = true;
                distance[nextc] = distance[cur] + 1;
                if(nextc == targ){
                    sol = distance[nextc];
                    break;
                }
            }
        }
        return sol;
    }
}
