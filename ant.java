// Barath Tirumala
// Assignment 2 CS2
// Ant

import java.util.*;

//edge class
class edge implements Comparable<edge> {

    //declare two vertices and weight of edge between them
    public int x;
    public int y;
    public int wght;

    public edge(int myx, int myy, int weight) {
        x = myx;
        y = myy;
        wght = weight;
    }

    //function used to compare weights in order to be sorted
    public int compareTo(edge other) {
        return this.wght - other.wght;
    }

}

//disjoint set class
class disjointset{
    final public static int N = 100;

    public int[] parent;

    // Creates a disjoint set of size n (0, 1, ..., n-1)
    public disjointset(int n) {
        parent = new int[n];
        for (int i=0; i<n; i++)
            parent[i] = i;
    }

    public int find(int v) {

        // root of tree
        if (parent[v] == v) return v;

        // Find my parent's root.
        int res = find(parent[v]);

        // reattach root
        parent[v] = res;
        return res;
    }

    public boolean union(int v1, int v2) {

        // Find respective roots.
        int rootv1 = find(v1);
        int rootv2 = find(v2);

        // No union done, v1, v2 already together.
        if (rootv1 == rootv2) return false;

        // Attach tree of v2 to tree of v1.
        parent[rootv2] = rootv1;
        return true;
    }
}

public class ant {

    //set max size
    final public static int N = 100;

    //main
    public static void main(String[] args) {

        //scan in number of test cases
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
        LinkedList list = new LinkedList();
        Queue<Integer> pq = new PriorityQueue<>();
        int[] edges = new int[1000];
        Boolean[] bool = new Boolean[100];
        for(int kk = 0; kk<100; kk++){
            bool[kk] = false;
        }
        int sum;

        //loop through cases
        for(int i = 1; i<=numCases; i++){
            //scan in number of hills and paths between them
            sum = 0;
            int numVertices = stdin.nextInt();
            int numEdges = stdin.nextInt();
            edge[] array = new edge[numEdges];
            //check for case when no edges;
            if(numEdges ==0 && numVertices ==1){
                System.out.println("Campus #" + i + ": " +"0");
                list.clear();
                pq.clear();
                sum = 0;
                continue;
            }
            //check if this is not possible
            if(!((numVertices*(numVertices-1)/2) >= numEdges)){
                System.out.println("Campus #" + i + ": " +"I'm a programmer, not a miracle worker!");
                list.clear();
                pq.clear();
                sum = 0;
                continue;
            }
            //read in values
            disjointset set = new disjointset(N);
            for(int k = 0; k<numEdges; k++){
                int edge1 = stdin.nextInt();
                int edge2 = stdin.nextInt();
                int tmpweight = stdin.nextInt();
                array[k] = new edge(edge1,edge2, tmpweight);
                int value = edge1*10 + edge2;
                pq.add(tmpweight);
                edges[tmpweight] = value;

            }

            Arrays.sort(array);

            while(list.size() < numVertices-1 && pq.size()>0){
                //System.out.println(list.size());
                int k = 0;
                int weight = pq.poll();
                int b = edges[weight]%10;
                int a = edges[weight]/10;
                //System.out.print(a +" " + b +" "+weight);

                //union into dijset
                boolean res = set.union(a,b);

                if(!res){
                    continue;
                }
                else{
                    sum += weight;
                    list.add(edges[weight]);
                    bool[a] = true;
                    bool[b] = true;
                }
                k++;

            }
            //System.out.println("Hiiii" + sum);
            //check fail cases
            int kj = 0;
            int flag = 0;
            int use = 0;
            for(int jk = 0; jk<100; jk++){

                if(bool[jk]){
                    kj++;
                    bool[jk] = false;
                }
            }
            for( int test = 1; test <numVertices-1; test++){
                if(set.parent[test] == set.parent[test+1]){
                    use++;
                }
                else{
                    flag = 1;
                }
            }
            if(kj == numVertices){
                System.out.println("Campus #" + i + ": " +sum);

            }
            else{
                System.out.println("Campus #" + i + ": " +"I'm a programmer, not a miracle worker!");
                //for(int qw = 0; qw<numVertices+1; qw++){
                  //  System.out.println(set.parent[qw]);
                //}
            }
            list.clear();
            pq.clear();

        }
    }
}


