//Barath Tirumala


import java.util.*;

public class secretchamber {
    public static void main(String[] args) {

        //scan in number of test cases
        Scanner stdin = new Scanner(System.in);
        int m = stdin.nextInt();
        int n = stdin.nextInt();
        int N = 26;
        boolean[][] G = new boolean[N][N];

        for(int i = 0; i<m; i++){
            Character temp1 = stdin.next().charAt(0);
            Character temp2 = stdin.next().charAt(0);

            int temp11 = lettonum(temp1);
            int temp22 = lettonum(temp2);

            //System.out.println(temp11);
            //System.out.println(temp22);
            temp11 --;
            temp22 --;

            G[temp11][temp22] = true;
        }
        for(int k = 0; k<n; k++){
            String Word1 = stdin.next();
            String Word2 = stdin.next();
            int check = 0;
            if(Word1.length() != Word2.length()){
                System.out.println("no");
                continue;
            }
            for(int len = 0; len<Word1.length(); len++){
                if(Word1.charAt(len) == Word2.charAt(len)){
                    check++;
                    continue;
                }
                else{
                    Character char1 = Word1.charAt(len);
                    Character char2 = Word2.charAt(len);

                    boolean used[] = new boolean[26];

                    int temp1 = lettonum(char1);
                    int temp2 = lettonum(char2);

                    temp1 --;
                    temp2 --;

                    boolean tempo = DFS(G, temp1, temp2, used);
                    if(tempo){
                        check++;
                    }
                }

            }
            if(check == Word1.length()){
                System.out.println("yes");
            }
            else {
                System.out.println("no");
            }

        }

    }

    public static boolean DFS(boolean[][] graph, int start, int end, boolean[] used){
        used[start] = true;
        for(int t = 0; t<26; t++){
            if (graph[start][t] && !used[t]){
                DFS(graph, t, end, used);
            }
        }
        if(used[end]){
            return true;
        }
        else{
            return false;
        }
    }

    public static int lettonum(char a) {
        final Map<Character, Integer> map = new HashMap<>();
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        map.put('d', 4);
        map.put('e', 5);
        map.put('f', 6);
        map.put('g', 7);
        map.put('h', 8);
        map.put('i', 9);
        map.put('j', 10);
        map.put('k', 11);
        map.put('l', 12);
        map.put('m', 13);
        map.put('n', 14);
        map.put('o', 15);
        map.put('p', 16);
        map.put('q', 17);
        map.put('r', 18);
        map.put('s', 19);
        map.put('t', 20);
        map.put('u', 21);
        map.put('v', 22);
        map.put('w', 23);
        map.put('x', 24);
        map.put('y', 25);
        map.put('z', 26);

        return map.get(a);

    }
    public static Character numtolet(int a) {
        final Map<Integer, Character> map = new HashMap<>();
        map.put(1, 'a');
        map.put(2, 'b');
        map.put(3, 'c');
        map.put(4, 'd');
        map.put(5, 'e');
        map.put(6, 'f');
        map.put(7, 'g');
        map.put(8, 'h');
        map.put(9, 'i');
        map.put(10, 'j');
        map.put(11, 'k');
        map.put(12, 'l');
        map.put(13, 'm');
        map.put(14, 'n');
        map.put(15, 'o');
        map.put(16, 'p');
        map.put(17, 'q');
        map.put(18, 'r');
        map.put(19, 's');
        map.put(20, 't');
        map.put(21, 'u');
        map.put(22, 'v');
        map.put(23, 'w');
        map.put(24, 'x');
        map.put(25, 'y');
        map.put(26, 'z');

        return map.get(a);
    }
}