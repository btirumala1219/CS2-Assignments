import java.util.*;

public class camera {
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        int numObj = stdin.nextInt(); // scan in number of objects
        int distance = stdin.nextInt();//distance between wall and gogol camera thing
        int beg = stdin.nextInt();
        int end = stdin.nextInt(); //beginning and end of the wall
        if((!inRange(numObj))){
            System.out.println("Too Large. Error.");
            return;
        }
        //object[] array = new object[numObj]; //create array of objects
        Queue<object> pq = new PriorityQueue<>();
        int numUnBlocked = 0;
        for(int i=0; i< numObj; i++){
            int x = stdin.nextInt(); //scan in the position of each object
            int y = stdin.nextInt();
            if(y < distance){
                numUnBlocked ++;
                continue;
            }
            pq.add(new object(x, y, beg, end, distance));
        }
        numObj -= numUnBlocked;
        object[] array = pq.toArray(new object[numObj]);
        for(int k=0; k<numObj; k++){
            if((!inRange(array[k].x))||(!inRange(array[k].y))||(!inRange(array[k].dist))||(!inRange(array[k].beg))||(!inRange(array[k].ending))){
                System.out.println("Too Large. Invalid Input. Error.");
                return;
            }
            array[k].getRange(); //get the range
            //System.out.println(array[k].x + "," + array[k].y +"  Beginning Point: "+ array[k].begpt+"   Ending Point: " + array[k].endpt + "   Range: "+ array[k].range);
        }
        Arrays.sort(array); //sort array based on the compareTo function
        int i = 0;
        int counter = 1;
        int numpics = 0;
        while(counter < numObj){
            if(array[i].endpt >= array[counter].begpt){ //when the max is the most common version
                counter++; //increment
            }
            else{
                numpics++; //increment
                i = counter; // check
            }
        }
        numpics++;
        System.out.println(numpics);
        return;
    }

    public static boolean inRange(int k){ //check if input is a valid input
        if(k < 100000){
            return true;
        }
        return false;
    }

}

class object implements Comparable<object>{ //object class
    public int x;
    public int y;
    public int beg;
    public int ending;
    public int dist;
    public double range;
    public double begpt;
    public double endpt;

    public object(int myx, int myy, int beginning, int end, int distance) { //read in all values that are relevant
        x = myx;
        y = myy;
        beg = beginning;
        ending = end;
        dist = distance;
    }

    public void getRange() { //calculate the range that the object is visible
        double ts1 = (double)(this.y - this.dist);
        double bs1 = (double)(this.x - this.beg)  ;
        double slope = ts1/bs1;
        double bs2 = (double)(this.x - this.ending)  ;
        double slope2 = ts1/bs2;
        this.begpt = ((-this.y)/slope) + this.x;
        this.endpt = ((-this.y)/slope2) + this.x;
        this.range = endpt-begpt;
    }

    public int compareTo(object other) { //compare to function to use to sort the objects
        if(this.begpt < other.begpt-1e-9) return -1;
        if(other.begpt < this.begpt-1e-9) return 1;
        return 0;
    }
}
